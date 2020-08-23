package hu.crs.montebanana.game.components;

import hu.crs.montebanana.game.movement.exception.IllegalLocationException;
import hu.crs.montebanana.game.movement.exception.IllegalStepException;
import hu.crs.montebanana.game.movement.Movement;
import hu.crs.montebanana.game.player.Player;
import hu.crs.montebanana.game.rendering.visitor.Renderable;
import hu.crs.montebanana.game.rendering.visitor.RendererVisitor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static hu.crs.montebanana.game.movement.Direction.LEFT;
import static hu.crs.montebanana.game.movement.Direction.RIGHT;

@RequiredArgsConstructor
public class Board implements Renderable {
    @Getter
    private final Player[] steps;
    final Map<String, Integer> playerLocation;

    public void step(Player player, Movement movement) {
        if (hasCard(player, movement)) {
            move(player, findEmptyLocation(location(player), movement));
        } else {
            throw new IllegalStepException("Step has been already played!");
        }
    }

    public boolean isValidMovement(Player player, Movement movement) {
        if (!hasCard(player, movement)) {
            return false;
        }

        try {
            findEmptyLocation(location(player), movement);
        } catch (IllegalLocationException e) {
            return false;
        }

        return true;
    }

    private boolean hasCard(Player player, Movement movement) {
        return player.getCards().contains(movement.getCount());
    }

    private int findEmptyLocation(int startingLocation, Movement movement) {
        if (RIGHT == movement.getDirection()) {
            return findEmptyLocationInDirection(startingLocation, movement.getCount(), i -> i <= 12, i -> ++i);
        } else if (LEFT == movement.getDirection()) {
            return findEmptyLocationInDirection(startingLocation, movement.getCount(), i -> i >= 0, i -> --i);
        }
        throw new IllegalArgumentException("Illegal direction!");
    }

    private int findEmptyLocationInDirection(int newLocation, int card, Predicate<Integer> isInBounds, Function<Integer, Integer> nextIndex) {
        int stepCount = 0;
        while (isInBounds.test(stepCount) && stepCount < card) {
            newLocation = nextIndex.apply(newLocation);

            if (newLocation < 0 || newLocation > 12) {
                throw new IllegalLocationException("No available position present!");
            }

            if (steps[newLocation] == null) {
                stepCount++;
            }
        }
        if (stepCount == card) {
            return newLocation;
        }
        throw new IllegalLocationException("No available position present!");
    }

    private void move(Player player, int to) {
        int from = playerLocation.get(player.getId());
        if (from > -1) steps[from] = null;
        playerLocation.put(player.getId(), to);
        steps[playerLocation.get(player.getId())] = player;
    }

    private int location(Player player) {
        return playerLocation.get(player.getId());
    }

    String winnerId() {
        Optional<Map.Entry<String, Integer>> maxValueEntry = playerLocation.entrySet().stream().max(Map.Entry.comparingByValue());
        if (maxValueEntry.isPresent()) {
            return maxValueEntry.get().getKey();
        }
        throw new RuntimeException("No max entry is available!");
    }

    void registerPlayer(Player player) {
        playerLocation.put(player.getId(), -1);
    }

    void reset() {
        Arrays.fill(steps, null);
        playerLocation.clear();
    }

    @Override
    public String accept(RendererVisitor rendererVisitor) {
        return rendererVisitor.visitBoard(this);
    }
}
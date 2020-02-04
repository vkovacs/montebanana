package hu.crs.montebanana.components;

import hu.crs.montebanana.movement.IllegalLocationException;
import hu.crs.montebanana.movement.IllegalStepException;
import hu.crs.montebanana.movement.Movement;
import hu.crs.montebanana.rendering.Renderable;
import hu.crs.montebanana.rendering.RendererVisitor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static hu.crs.montebanana.movement.Direction.LEFT;
import static hu.crs.montebanana.movement.Direction.RIGHT;

@RequiredArgsConstructor
public class Mountain implements Renderable {
    @Getter
    private final Player[] steps;
    final Map<String, Integer> playerLocation;

    void step(Player player, Movement movement) {
        if (player.getCards().contains(movement.getCard())) {
            move(player, findEmptyLocation(location(player), movement));
            player.removeCard(movement.getCard());
        } else {
            throw new IllegalStepException("Step has been already played!");
        }
    }

    private int findEmptyLocation(int newLocation, Movement movement) {
        if (RIGHT == movement.getDirection()) {
            return findEmptyLocationInDirection(newLocation, movement.getCard(), i -> i < 12, i -> ++i);
        } else if (LEFT == movement.getDirection()) {
            return findEmptyLocationInDirection(newLocation, movement.getCard(), i -> i > 0, i -> --i);
        }
        throw new IllegalArgumentException("Illegal direction!");
    }

    private int findEmptyLocationInDirection(int newLocation, int card, Predicate<Integer> isInBounds, Function<Integer, Integer> nextIndex) {
        int stepCount = 0;
        while (isInBounds.test(stepCount) && stepCount < card) {
            newLocation = nextIndex.apply(newLocation);
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

    @Override
    public String accept(RendererVisitor rendererVisitor) {
        return rendererVisitor.visitMountain(this);
    }
}
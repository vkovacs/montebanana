package hu.crs.montebanana.components;

import hu.crs.montebanana.movement.Direction;
import hu.crs.montebanana.movement.IllegalLocationException;
import hu.crs.montebanana.movement.IllegalStepException;
import hu.crs.montebanana.movement.Movement;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

import static hu.crs.montebanana.movement.Direction.RIGHT;

@RequiredArgsConstructor
class Mountain {
    private final Player[] mountain;
    final Map<Integer, Integer> playerLocation;

    void step(Player player, Movement movement) {
        if (player.getCards().contains(movement.getCard())) {
            int oldLocation = location(player);

            int newLocation = findNextEmptyLocation(oldLocation, movement.getCard(), movement.getDirection());

            move(player, oldLocation, newLocation);
            player.removeCard(movement.getCard());
        } else {
            throw new IllegalStepException("Step has been already played!");
        }
    }

    private int findNextEmptyLocation(int newLocation, int card, Direction direction) {
        int stepCount = 0;
        if (RIGHT == direction) {
            while (newLocation < 12 && stepCount < card) {
                newLocation++;
                if (mountain[newLocation] == null) {
                    stepCount++;
                }
            }
            if (stepCount == card) {
                return newLocation;
            }
        } else {
            while (newLocation > 0 && stepCount < card) {
                newLocation--;
                if (mountain[newLocation] == null) {
                    stepCount++;
                }
            }
            if (stepCount == card) {
                return newLocation;
            }
        }

        throw new IllegalLocationException("No available position present!");
    }

    private void move(Player player, int from, int to) {
        if (from > -1) mountain[from] = null;
        playerLocation.put(player.getId(), to);
        mountain[playerLocation.get(player.getId())] = player;
    }

    private int location(Player player) {
        return playerLocation.get(player.getId());
    }

    Integer winnerId() {
        Optional<Map.Entry<Integer, Integer>> maxValueEntry = playerLocation.entrySet().stream().max(Map.Entry.comparingByValue());
        if (maxValueEntry.isPresent()) {
            return maxValueEntry.get().getKey();
        }
        throw new RuntimeException("No max entry is available!");
    }

    void registerPlayer(Player player) {
        playerLocation.put(player.getId(), -1);
    }

    String render() {
        StringBuilder stringBuilder = new StringBuilder(26);
        for (Player c : mountain) {
            if (c != null) stringBuilder.append(c.asString()).append(" ");
            else stringBuilder.append("  ");
        }
        return stringBuilder.toString();
    }
}
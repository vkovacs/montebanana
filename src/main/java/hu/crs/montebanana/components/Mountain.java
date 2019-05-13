package hu.crs.montebanana.components;

import hu.crs.montebanana.movement.IllegalLocationException;
import hu.crs.montebanana.movement.IllegalStepException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntBinaryOperator;

public class Mountain {
    private final Player[] mountain = new Player[13];
    private final Map<Integer, Integer> playerLocation = new HashMap<>();
    {
        playerLocation.put(0, -1);
    }

    void step(Player player, int step, IntBinaryOperator op) {
        if (player.getAvailableSteps().contains(step)) {
            int oldLocation = location(player);
            int newLocation = op.applyAsInt(oldLocation, step);
            if (newLocation < 0 || newLocation > 12) throw new IllegalLocationException("Illegal destination index!");
            move(player, oldLocation, newLocation);
            player.removeCard(step);
        } else {
            throw new IllegalStepException("Step has been already played!");
        }
    }

    private void move(Player player, int from, int to) {
        if (from > -1) mountain[from] = null;
        playerLocation.put(player.getId(), to);
        mountain[playerLocation.get(player.getId())] = player;
    }

    private int location(Player player) {
        return playerLocation.get(player.getId());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(26);
        for (Player c : mountain) {
            if (c != null) stringBuilder.append(c.toString()).append(" ");
            else stringBuilder.append("  ");
        }
        return stringBuilder.toString();
    }
}
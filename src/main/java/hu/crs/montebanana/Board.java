package hu.crs.montebanana;

import hu.crs.montebanana.pieces.IllegalLocationException;
import hu.crs.montebanana.pieces.IllegalStepException;
import hu.crs.montebanana.pieces.Player;
import lombok.Getter;
import tool.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntBinaryOperator;

import static java.lang.String.format;

public class Board {

    @Getter
    private final List<Player> players = List.of(new Player(0, Color.RED));
    private final Player[] mountain = new Player[13];
    private final Map<Integer, Integer> playerLocation = new HashMap<>();
    {
        playerLocation.put(0, -1);
    }

    void stepLeft(Player player, int steps) {
        step(player, steps, (a, b) -> a - b);
    }

    void stepRight(Player player, int steps) {
        step(player, steps, Integer::sum);
    }

    private void step(Player player, int step, IntBinaryOperator op) {
        if (player.getAvailableCards().contains(step)) {
            int newLocation = op.applyAsInt(location(player), step);
            if (newLocation < 0 || newLocation > 12) throw new IllegalLocationException("Illegal destination index!");
            if (location(player) > -1) mountain[location(player)] = null;
            playerLocation.put(player.getId(), newLocation);
            mountain[playerLocation.get(player.getId())] = player;
            player.removeCard(step);
        } else {
            throw new IllegalStepException("Step has been already played!");
        }
    }



    @Override
    public String toString() {

        String cards = format("Available cards: %s", players.get(0).getAvailableCards());
        String mountainLine = toString(mountain);
        String stepsLine = "_ _ _ _ _ _ _ _ _ _ _ _ _";
        return cards + "\n"
                + mountainLine + "\n"
                + stepsLine;
    }

    private static String toString(Player[] players) {
        StringBuilder stringBuilder = new StringBuilder(26);
        for (Player c : players) {
            if (c != null) stringBuilder.append(c.toString()).append(" ");
            else stringBuilder.append("  ");
        }
        return stringBuilder.toString();
    }

    private int location(Player player) {
        return playerLocation.get(player.getId());
    }
}

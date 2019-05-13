package hu.crs.montebanana;

import hu.crs.montebanana.pieces.IllegalLocationException;
import hu.crs.montebanana.pieces.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntBinaryOperator;

import static java.lang.String.format;

public class Board {
    private int turn = 1;
    private List<Player> players = List.of(new Player(0, Player.Color.RED));
    private Player[] mountain = new Player[13];
    private Map<Integer, Integer> playerLocation = new HashMap<>();
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
        int newLocation = op.applyAsInt(location(player), step);
        if (newLocation < 0 || newLocation > 12) throw new IllegalLocationException();
        if (location(player) > -1) mountain[location(player)] = null;
        playerLocation.put(player.getId(), newLocation);
        mountain[playerLocation.get(player.getId())] = player;
    }

    int nextTurn() {
        return turn++;
    }

    @Override
    public String toString() {
        String turnLine = format("Turn: %d", turn);
        String mountainLine = toString(mountain);
        String stepsLine = "_ _ _ _ _ _ _ _ _ _ _ _ _";
        return turnLine + "\n"
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

    int getTurn() {
        return turn;
    }

    List<Player> getPlayers() {
        return players;
    }

    private int location(Player player) {
        return playerLocation.get(player.getId());
    }
}

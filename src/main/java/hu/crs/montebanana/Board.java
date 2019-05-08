package hu.crs.montebanana;

import hu.crs.montebanana.pieces.IllegalLocationException;
import hu.crs.montebanana.pieces.Player;

import java.util.List;
import java.util.function.IntBinaryOperator;

import static java.lang.String.format;

public class Board {
    private int turn = 1;
    private List<Player> players = List.of(new Player());
    private Player[] mountain = new Player[13];

    void stepLeft(Player player, int steps) {
        step(player, steps, (a, b) -> a - b);
    }

    void stepRight(Player player, int steps) {
        step(player, steps, Integer::sum);
    }

    private void step(Player player, int step, IntBinaryOperator op) {
        int location = op.applyAsInt(player.getLocation(), step);
        if (location < 0 || location > 12) throw new IllegalLocationException();
        if (player.getLocation() > -1) mountain[player.getLocation()] = null;
        player.setLocation(location);
        mountain[player.getLocation()] = player;
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
            if (c != null) stringBuilder.append("@ ");
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
}

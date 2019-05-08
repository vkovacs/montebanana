package hu.crs.montebanana;

import hu.crs.montebanana.pieces.IllegalLocationException;
import hu.crs.montebanana.pieces.Player;

import java.util.List;

import static java.lang.String.format;

public class Board {
    private int turn = 1;
    private List<Player> players = List.of(new Player());
    private Player[] mountain = new Player[13];

    public void stepLeft(Player player, int steps) {
        int location = player.getLocation() - Math.abs(steps);
        if (location < 0) throw new IllegalLocationException();
        if (player.getLocation() > -1) mountain[player.getLocation()] = null;
        player.setLocation(location);
        mountain[player.getLocation()] = player;

    }

    public void stepRight(Player player, int steps) {
        int location = player.getLocation() + Math.abs(steps);
        if (location > 12) throw new IllegalLocationException();
        if (player.getLocation() > -1) mountain[player.getLocation()] = null;
        player.setLocation(location);
        mountain[player.getLocation()] = player;
    }

    public int nextTurn() {
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

    public int getTurn() {
        return turn;
    }

    public List<Player> getPlayers() {
        return players;
    }
}

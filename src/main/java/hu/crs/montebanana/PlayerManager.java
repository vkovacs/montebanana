package hu.crs.montebanana;

import hu.crs.montebanana.components.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    List<Player> players = new ArrayList<>();
    private int actualPlayer = -1;

    public void register(Player player) {
        players.add(player);
        if (actualPlayer < 0) {
            actualPlayer = 0;
        }
    }

    public Player actualPlayer() {
        if (players.isEmpty()) {
            throw new NoSuchPlayerException("No player is registered!");
        }

        return players.get(actualPlayer);
    }

    public void actualPlayerMoved() {
        if (players.isEmpty()) {
            throw new NoSuchPlayerException("No player is registered!");
        }

        if (++actualPlayer >= players.size()) {
            actualPlayer = 0;
        }
    }

    public void reset() {
        players.clear();
        actualPlayer = -1;
    }
}

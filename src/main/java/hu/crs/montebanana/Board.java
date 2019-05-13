package hu.crs.montebanana;

import hu.crs.montebanana.pieces.Mountain;
import hu.crs.montebanana.pieces.Player;
import lombok.Getter;
import tool.Color;

import java.util.List;

import static java.lang.String.format;

public class Board {

    @Getter
    private final List<Player> players = List.of(new Player(0, Color.RED));
    private final Mountain mountain = new Mountain();

    void stepLeft(Player player, int steps) {
        mountain.step(player, steps, (a, b) -> a - b);
    }

    void stepRight(Player player, int steps) {
        mountain.step(player, steps, Integer::sum);
    }

    @Override
    public String toString() {
        String cards = format("Available cards: %s", players.get(0).getAvailableCards());
        String mountainLine = mountain.toString();
        String stepsLine = "_ _ _ _ _ _ _ _ _ _ _ _ _";
        return cards + "\n"
                + mountainLine + "\n"
                + stepsLine;
    }

}

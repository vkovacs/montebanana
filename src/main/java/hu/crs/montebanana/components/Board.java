package hu.crs.montebanana.components;

import lombok.Getter;
import tool.Color;

import java.util.List;

import static java.lang.String.format;

public class Board {

    @Getter
    private final List<Player> players = List.of(new Player(0, Color.RED));
    private final Mountain mountain = new Mountain();

    public void stepLeft(Player player, int steps) {
        mountain.step(player, steps, (a, b) -> a - b);
    }

    public void stepRight(Player player, int steps) {
        mountain.step(player, steps, Integer::sum);
    }

    @Override
    public String toString() {
        String availableStepsLine = format("Available steps: %s", players.get(0).getAvailableSteps());
        String mountainLine = mountain.toString();
        String stepsLine = "_ _ _ _ _ _ _ _ _ _ _ _ _";
        return availableStepsLine + "\n"
                + mountainLine + "\n"
                + stepsLine;
    }
}

package hu.crs.montebanana.components;

import lombok.Getter;
import tool.Color;

import java.util.List;

import static hu.crs.montebanana.movement.Direction.LEFT;
import static hu.crs.montebanana.movement.Direction.RIGHT;
import static java.lang.String.format;
import static tool.ColorTools.colorText;

public class Board {

    @Getter
    private final List<Player> players = List.of(new Player(0, Color.RED), new Player(1, Color.BLUE));
    private final Mountain mountain = new Mountain();
    private int actualPlayerIndex = 0;


    public void stepLeft(Player player, int steps) {
        mountain.step(player, steps, LEFT);
    }

    public void stepRight(Player player, int steps) {
        mountain.step(player, steps, RIGHT);
    }

    public Player actualPlayer() {
        return players.get(actualPlayerIndex);
    }

    public Player nextPlayer() {
        if (actualPlayerIndex < players.size() - 1) {
            return players.get(++actualPlayerIndex);
        }
        actualPlayerIndex = 0;
        return players.get(actualPlayerIndex);
    }

    public boolean playersHaveSteps() {
        return players.stream().map(player -> player.getAvailableSteps().size()).reduce(0, Integer::sum) > 0;
    }

    public String winnerToString() {
        Integer winnerId = mountain.winnerId();
        Player winner = players.stream().filter(p -> p.getId() == winnerId).findFirst().orElseThrow(() -> new RuntimeException("No player by id!"));

        return colorText("The winner is: " + winner.toString(), winner.getColor());

    }

    @Override
    public String toString() {
        String availableStepsLine = format("Available steps: %s", actualPlayer().getAvailableSteps());
        String mountainLine = mountain.toString();
        String stepsLine = "_ _ _ _ _ _ _ _ _ _ _ _ _";
        return colorText(availableStepsLine + "\n", actualPlayer().getColor())
                + mountainLine + "\n"
                + stepsLine;
    }
}

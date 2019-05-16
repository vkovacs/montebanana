package hu.crs.montebanana.components;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static hu.crs.montebanana.movement.Direction.LEFT;
import static hu.crs.montebanana.movement.Direction.RIGHT;
import static java.lang.String.format;
import static tool.ColorTools.colorText;

public class Board {

    @Getter
    private final List<Player> players = new ArrayList<>();
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

    public Player winner() {
        Integer winnerId = mountain.winnerId();

        return players.stream().filter(p -> p.getId() == winnerId).findFirst().orElseThrow(() -> new RuntimeException("No player by id!"));
    }

    public void registerPlayer(Player player) {
        players.add(player);
        mountain.registerPlayer(player);
    }

    public void reset() {
        players.forEach(Player::reset);
    }

    public String asString() {
        String availableStepsLine = format("Available steps: %s", actualPlayer().getAvailableSteps());
        String mountainLine = mountain.asString();
        String stepsLine = "_ _ _ _ _ _ _ _ _ _ _ _ _";
        return colorText(availableStepsLine + "\n", actualPlayer().getColor())
                + mountainLine + "\n"
                + stepsLine;
    }
}

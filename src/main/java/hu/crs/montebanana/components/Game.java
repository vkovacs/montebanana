package hu.crs.montebanana.components;

import hu.crs.montebanana.movement.IllegalStepException;
import hu.crs.montebanana.movement.Movement;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.String.format;
import static tool.ColorTools.colorText;

public class Game {

    @Getter
    private Board board;
    @Getter
    private int lastCard = -1;
    private int actualPlayerIndex = 0;

    public Game() {
        Mountain mountain = new Mountain(new Player[13], new HashMap<>());
        board = new Board(new ArrayList<>(), mountain);
    }

    public void registerPlayer(Player player) {
        board.registerPlayer(player);
    }

    public Player actualPlayer() {
        return board.getPlayers().get(actualPlayerIndex);
    }

    public Player nextPlayer() {
        if (actualPlayerIndex < board.getPlayers().size() - 1) {
            return board.getPlayers().get(++actualPlayerIndex);
        }
        actualPlayerIndex = 0;
        return board.getPlayers().get(actualPlayerIndex);
    }

    public void newTurn() {
        lastCard = -1;
        board.getPlayers().forEach(Player::getBackAllCards);
    }

    public boolean playersHaveSteps() {
        return board.getPlayers().stream().map(player -> player.getCards().size()).reduce(0, Integer::sum) > 0;
    }

    public Player winner() {
        Integer winnerId = board.getMountain().winnerId();

        return board.getPlayers().stream().filter(p -> p.getId() == winnerId).findFirst().orElseThrow(() -> new RuntimeException("No player by id!"));
    }

    public void step(Player player, Movement movement) {
        if (movement.getCard() == lastCard) throw new IllegalStepException("Cannot use the same card as the previous player!");

        board.step(player, movement);
        lastCard = movement.getCard();
    }

    public List<Player> getPlayers() {
        return board.getPlayers();
    }

    public String render() {
        String availableStepsLine = format("Available steps: %s", actualPlayer().getCards());
        String mountainLine = board.getMountain().render();
        String stepsLine = "_ _ _ _ _ _ _ _ _ _ _ _ _";
        return colorText(availableStepsLine + "\n", actualPlayer().getColor())
                + mountainLine + "\n"
                + stepsLine;
    }
}

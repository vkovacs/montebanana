package hu.crs.montebanana.components;

import hu.crs.montebanana.movement.IllegalStepException;
import hu.crs.montebanana.movement.Movement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static tool.ColorTools.colorText;

@RequiredArgsConstructor
public class Board {

    @Getter
    private final List<Player> players;
    private final Mountain mountain;
    private int actualPlayerIndex = 0;
    @Getter
    private int lastCard = -1;

    public void step(Player player, Movement movement) {
        int card = movement.getCard();
        if (card == lastCard) throw new IllegalStepException("Cannot use the same card as the previous player!");
        mountain.step(player, movement);
        lastCard = card;
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
        return players.stream().map(player -> player.getCards().size()).reduce(0, Integer::sum) > 0;
    }

    public Player winner() {
        Integer winnerId = mountain.winnerId();

        return players.stream().filter(p -> p.getId() == winnerId).findFirst().orElseThrow(() -> new RuntimeException("No player by id!"));
    }

    public void registerPlayer(Player player) {
        players.add(player);
        mountain.registerPlayer(player);
    }

    public void newTurn() {
        lastCard = -1;
        players.forEach(Player::getBackAllCards);
    }

    public String asString() {
        String availableStepsLine = format("Available steps: %s", actualPlayer().getCards());
        String mountainLine = mountain.asString();
        String stepsLine = "_ _ _ _ _ _ _ _ _ _ _ _ _";
        return colorText(availableStepsLine + "\n", actualPlayer().getColor())
                + mountainLine + "\n"
                + stepsLine;
    }
}

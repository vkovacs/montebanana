package hu.crs.montebanana.game.player;

import hu.crs.montebanana.game.components.Board;
import hu.crs.montebanana.game.movement.exception.IllegalStepException;
import hu.crs.montebanana.game.movement.strategy.ConstantMovementStrategy;
import hu.crs.montebanana.game.movement.strategy.NoOpMovementStrategy;
import hu.crs.montebanana.game.rendering.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class PlayerTest {

    @Test
    public void removeCard() {
        Player player = new Player(Color.RED, new NoOpMovementStrategy());
        player.removeCard(4);

        assertThat(player.getCards(), containsInAnyOrder(1, 2, 3, 5));
    }

    @Test
    public void receiveBanana() {
        Player player = new Player(Color.RED, new NoOpMovementStrategy());
        assertThat(player.getBananas(), is(0));

        player.receiveBanana();
        assertThat(player.getBananas(), is(1));
    }

    @Test
    public void getBackAllCards() {
        Player player = new Player(Color.RED, new NoOpMovementStrategy());
        player.removeCard(1);
        player.removeCard(5);
        player.getBackAllCards();

        assertThat(player.getCards(), containsInAnyOrder(1, 2, 3, 4, 5));
    }

    @Test
    void shouldThrowExceptionIfPlayerHasMultipleCardsAndTheChosenOneIsTheSameAsLastPlayerCard() {
        Player player = new Player(Color.RED, new ConstantMovementStrategy());
        Board board = new Board(new Player[13], new HashMap<>());

        Assertions.assertThrows(IllegalStepException.class, () -> player.step(board, 1), "Cannot use the same card as the previous player!");
    }

    @Test
    void ifPlayerHasOneCardAndItIsTheSameAsLastPlayerCardItsAllowed() {
        Player player = new Player(Color.RED, new ConstantMovementStrategy());
        player.removeCard(2);
        player.removeCard(3);
        player.removeCard(4);
        player.removeCard(5);

        Board board = new Board(new Player[13], new HashMap<>());
        board.registerPlayer(player);
        Assertions.assertEquals(1, player.step(board, 1));
    }

}
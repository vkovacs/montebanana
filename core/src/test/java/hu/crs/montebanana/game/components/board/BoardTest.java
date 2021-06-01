package hu.crs.montebanana.game.components.board;

import hu.crs.montebanana.game.movement.Movement;
import hu.crs.montebanana.game.movement.exception.IllegalLocationException;
import hu.crs.montebanana.game.player.Player;
import hu.crs.montebanana.game.rendering.Color;
import hu.crs.montebanana.game.movement.strategy.ConstantMovementStrategy;
import hu.crs.montebanana.game.movement.strategy.NoOpMovementStrategy;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static hu.crs.montebanana.game.movement.Direction.LEFT;
import static hu.crs.montebanana.game.movement.Direction.RIGHT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {

    @Test
    public void findNextEmptyLocationIllegalLeftStepFromStartingPosition() {
        Player player = new Player(Color.RED, new NoOpMovementStrategy());
        Player[] players = new Player[13];
        Map<String, Integer> playerLocation = new HashMap<>();

        playerLocation.put(player.getId(), -1);
        Board board = new Board(players, playerLocation);

        assertThrows(IllegalLocationException.class, () -> board.step(player, new Movement(1, LEFT)));
    }

    @Test
    public void findNextEmptyLocationIllegalRightStepFromEndingPosition() {
        Player player = new Player(Color.RED, new NoOpMovementStrategy());
        Player[] players = new Player[13];
        Map<String, Integer> playerLocation = new HashMap<>();

        playerLocation.put(player.getId(), 12);
        Board board = new Board(players, playerLocation);

        assertThrows(IllegalLocationException.class, () -> board.step(player, new Movement(1, RIGHT)));
    }

    @Test
    public void findNextEmptyLocationLegalStepToRightFromStartingPosition() {
        Player player = new Player(Color.RED, new NoOpMovementStrategy());
        Player[] players = new Player[13];
        Map<String, Integer> playerLocation = new HashMap<>();

        playerLocation.put(player.getId(), -1);
        Board board = new Board(players, playerLocation);

        board.step(player, new Movement(2, RIGHT));
        assertThat(board.playerLocation.get(player.getId()), Matchers.is(1));
    }

    @Test
    public void findNextEmptyLocationLegalStepToLeftFromEndingPosition() {
        Player player = new Player(Color.RED, new NoOpMovementStrategy());
        Player[] players = new Player[13];
        Map<String, Integer> playerLocation = new HashMap<>();

        playerLocation.put(player.getId(), 12);
        Board board = new Board(players, playerLocation);

        board.step(player, new Movement(2, LEFT));
        assertThat(board.playerLocation.get(player.getId()), Matchers.is(10));
    }

    @Test
    void shouldThrowExceptionIfPlayerHasMultipleCardsAndTheChosenOneIsTheSameAsLastPlayerCard() {
        Player player = new Player(Color.RED, new ConstantMovementStrategy());
        Board board = new Board(new Player[13], new HashMap<>());
        board.setLastPlayedCard(1);

        Assertions.assertEquals(StepStatus.PLAYED_SAME_CARD_AS_PREVIOUS_PLAYER_AND_NOT_PLAYERS_LAST_CARD, board.isValidMovement(player, new Movement(1, RIGHT)));
    }

    @Test
    void ifPlayerHasOneCardAndItIsTheSameAsLastPlayerCardItsAllowed() {
        Player player = new Player(Color.RED, new ConstantMovementStrategy(), Set.of(1));

        Board board = new Board(new Player[13], new HashMap<>());
        board.registerPlayer(player);
        Assertions.assertEquals(StepStatus.VALID, board.isValidMovement(player, new Movement(1, RIGHT)));
    }
}
package hu.crs.montebanana.game.components.board;

import hu.crs.montebanana.game.movement.Movement;
import hu.crs.montebanana.game.movement.exception.IllegalLocationException;
import hu.crs.montebanana.game.movement.strategy.NoOpMovementStrategy;
import hu.crs.montebanana.game.player.Player;
import hu.crs.montebanana.game.rendering.Color;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static hu.crs.montebanana.game.movement.Direction.LEFT;
import static hu.crs.montebanana.game.movement.Direction.RIGHT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
        assertThat(board.playerLocation.get(player.getId()), is(1));
    }

    @Test
    public void findNextEmptyLocationLegalStepToLeftFromEndingPosition() {
        Player player = new Player(Color.RED, new NoOpMovementStrategy());
        Player[] players = new Player[13];
        Map<String, Integer> playerLocation = new HashMap<>();

        playerLocation.put(player.getId(), 12);
        Board board = new Board(players, playerLocation);

        board.step(player, new Movement(2, LEFT));
        assertThat(board.playerLocation.get(player.getId()), is(10));
    }
}
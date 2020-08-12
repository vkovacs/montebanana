package hu.crs.montebanana.game.player;

import hu.crs.montebanana.game.movement.strategy.NoOpMovementStrategy;
import hu.crs.montebanana.game.player.exception.NoSuchPlayerException;
import hu.crs.montebanana.game.player.exception.PlayerColorException;
import hu.crs.montebanana.game.player.exception.TooManyRegisteredPlayerException;
import hu.crs.montebanana.game.rendering.Color;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerManagerTest {
    PlayerManager playerManager = new PlayerManager();

    @BeforeEach
    public void setUp() throws Exception {
        playerManager.reset();
    }

    @Test
    public void playerManagerRegisterPlayer() {
        Player player0 = new Player(Color.BLUE, new NoOpMovementStrategy());
        playerManager.register(player0);
        assertThat(playerManager.players, Matchers.hasSize(1));
        assertThat(playerManager.players.contains(player0), is(Boolean.TRUE));
    }

    @Test
    public void getDefaultActualPlayerWithoutRegisteredPlayer() {
        assertThrows(NoSuchPlayerException.class, () -> playerManager.actualPlayer(), "No player is registered!");
    }

    @Test
    public void getDefaultActualPlayer() {
        Player player0 = new Player(Color.BLUE, new NoOpMovementStrategy());
        playerManager.register(player0);

        Player actualPlayer = playerManager.actualPlayer();
        assertThat(player0, is(actualPlayer));
    }

    @Test
    public void getDefaultActualPlayerMovedWhenNoPlayerIsRegisteredReturnsException() {
        assertThrows(NoSuchPlayerException.class, () -> playerManager.actualPlayerMoved(), "No player is registered!");
    }

    @Test
    public void getActualPlayerAfterActualPlayerMovedIsTheSamePlayerIfNoOtherPlayerRegistered() {
        Player player0 = new Player(Color.BLUE, new NoOpMovementStrategy());
        playerManager.register(player0);

        playerManager.actualPlayerMoved();
        assertThat(playerManager.actualPlayer(), is(player0));
    }

    @Test
    public void getActualPlayerAfterFirstPlayersMoved() {
        Player player0 = new Player(Color.BLUE, new NoOpMovementStrategy());
        playerManager.register(player0);

        Player player1 = new Player(Color.RED, new NoOpMovementStrategy());
        playerManager.register(player1);

        playerManager.actualPlayerMoved();

        Player actualPlayer = playerManager.actualPlayer();
        assertThat(player1, is(actualPlayer));
    }

    @Test
    public void afterResetNoPlayerIsRegistered() {
        Player player0 = new Player(Color.BLUE, new NoOpMovementStrategy());
        playerManager.register(player0);
        playerManager.reset();

        assertThrows(NoSuchPlayerException.class, () -> playerManager.actualPlayer(), "No player is registered!");
    }

    @Test
    public void afterResetActualPlayerIsReset() {
        Player player0 = new Player(Color.BLUE, new NoOpMovementStrategy());
        playerManager.register(player0);

        Player player1 = new Player(Color.RED, new NoOpMovementStrategy());
        playerManager.register(player1);

        playerManager.actualPlayerMoved();

        playerManager.reset();

        Player player0ReRegistered = new Player(Color.BLUE, new NoOpMovementStrategy());
        playerManager.register(player0ReRegistered);

        Player player1ReRegistered = new Player(Color.RED, new NoOpMovementStrategy());
        playerManager.register(player1ReRegistered);

        assertThat(playerManager.actualPlayer(), is(player0ReRegistered));
    }

    @Test
    public void fourPlayerCanBeRegistered() {
        Player player0 = new Player(Color.BLUE, new NoOpMovementStrategy());
        Player player1 = new Player(Color.RED, new NoOpMovementStrategy());
        Player player2 = new Player(Color.GREEN, new NoOpMovementStrategy());
        Player player3 = new Player(Color.YELLOW, new NoOpMovementStrategy());
        playerManager.register(player0);
        playerManager.register(player1);
        playerManager.register(player2);
        playerManager.register(player3);
    }

    @Test
    public void maximumRegisteredUserCountIsFour() {
        Player player0 = new Player(Color.BLUE, new NoOpMovementStrategy());
        Player player1 = new Player(Color.RED, new NoOpMovementStrategy());
        Player player2 = new Player(Color.GREEN, new NoOpMovementStrategy());
        Player player3 = new Player(Color.YELLOW, new NoOpMovementStrategy());
        Player player4 = new Player(Color.RED_BOLD, new NoOpMovementStrategy());
        playerManager.register(player0);
        playerManager.register(player1);
        playerManager.register(player2);
        playerManager.register(player3);

        assertThrows(TooManyRegisteredPlayerException.class, () -> playerManager.register(player4), "Too many registered player!");
    }

    @Test
    public void playersMustHaveDifferentColors() {
        Player player0 = new Player(Color.BLUE, new NoOpMovementStrategy());
        Player player1 = new Player(Color.BLUE, new NoOpMovementStrategy());
        playerManager.register(player0);

        assertThrows(PlayerColorException.class, () -> playerManager.register(player1), "Two player cannot have the same color!");

    }
}
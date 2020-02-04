package hu.crs.montebanana;

import hu.crs.montebanana.components.Player;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tool.Color;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PlayerManagerTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    PlayerManager playerManager = new PlayerManager();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void playerManagerRegisterPlayer() {
        Player player0 = new Player(0, Color.BLUE);
        playerManager.register(player0);
        assertThat(playerManager.players, Matchers.hasSize(1));
        assertThat(playerManager.players.contains(player0), is(Boolean.TRUE));
    }

    @Test
    public void getDefaultActualPlayerWithoutRegisteredPlayer() {
        expectedException.expect(NoSuchPlayerException.class);
        expectedException.expectMessage("No player is registered!");

        playerManager.actualPlayer();
    }

    @Test
    public void getDefaultActualPlayer() {
        Player player0 = new Player(0, Color.BLUE);
        playerManager.register(player0);

        Player actualPlayer = playerManager.actualPlayer();
        assertThat(player0, is(actualPlayer));
    }

    @Test
    public void getDefaultActualPlayerMovedWhenNoPlayerIsRegisteredReturnsException() {
        expectedException.expect(NoSuchPlayerException.class);
        expectedException.expectMessage("No player is registered!");

        playerManager.actualPlayerMoved();

    }

    @Test
    public void getActualPlayerAfterActualPlayerMovedIsTheSamePlayerIfNoOtherPlayerRegistered() {
        Player player0 = new Player(0, Color.BLUE);
        playerManager.register(player0);

        playerManager.actualPlayerMoved();
        assertThat(playerManager.actualPlayer(), is(player0));
    }

    @Test
    public void getActualPlayerAfterFirstPlayersMoved() {
        Player player0 = new Player(0, Color.BLUE);
        playerManager.register(player0);

        Player player1 = new Player(1, Color.RED);
        playerManager.register(player1);

        playerManager.actualPlayerMoved();

        Player actualPlayer = playerManager.actualPlayer();
        assertThat(player1, is(actualPlayer));
    }

    @Test
    public void afterResetNoPlayerIsRegistered() {
        expectedException.expect(NoSuchPlayerException.class);
        expectedException.expectMessage("No player is registered!");

        Player player0 = new Player(0, Color.BLUE);
        playerManager.register(player0);
        playerManager.reset();
        playerManager.actualPlayer();
    }

    @Test
    public void afterResetActualPlayerIsReset() {
        Player player0 = new Player(0, Color.BLUE);
        playerManager.register(player0);

        Player player1 = new Player(1, Color.RED);
        playerManager.register(player1);

        playerManager.actualPlayerMoved();

        playerManager.reset();

        Player player0ReRegistered = new Player(0, Color.BLUE);
        playerManager.register(player0ReRegistered);

        Player player1ReRegistered = new Player(1, Color.RED);
        playerManager.register(player1ReRegistered);

        assertThat(playerManager.actualPlayer(), is(player0ReRegistered));

    }
}
package hu.crs.montebanana.player;

import hu.crs.montebanana.rendering.TextRendererVisitor;
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
        playerManager.reset();
    }

    @Test
    public void playerManagerRegisterPlayer() {
        Player player0 = new Player(Color.BLUE, new TextRendererVisitor(new PlayerManager()));
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
        Player player0 = new Player(Color.BLUE, new TextRendererVisitor(new PlayerManager()));
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
        Player player0 = new Player(Color.BLUE, new TextRendererVisitor(new PlayerManager()));
        playerManager.register(player0);

        playerManager.actualPlayerMoved();
        assertThat(playerManager.actualPlayer(), is(player0));
    }

    @Test
    public void getActualPlayerAfterFirstPlayersMoved() {
        Player player0 = new Player(Color.BLUE, new TextRendererVisitor(new PlayerManager()));
        playerManager.register(player0);

        Player player1 = new Player(Color.RED, new TextRendererVisitor(new PlayerManager()));
        playerManager.register(player1);

        playerManager.actualPlayerMoved();

        Player actualPlayer = playerManager.actualPlayer();
        assertThat(player1, is(actualPlayer));
    }

    @Test
    public void afterResetNoPlayerIsRegistered() {
        expectedException.expect(NoSuchPlayerException.class);
        expectedException.expectMessage("No player is registered!");

        Player player0 = new Player(Color.BLUE, new TextRendererVisitor(new PlayerManager()));
        playerManager.register(player0);
        playerManager.reset();
        playerManager.actualPlayer();
    }

    @Test
    public void afterResetActualPlayerIsReset() {
        Player player0 = new Player(Color.BLUE, new TextRendererVisitor(new PlayerManager()));
        playerManager.register(player0);

        Player player1 = new Player(Color.RED, new TextRendererVisitor(new PlayerManager()));
        playerManager.register(player1);

        playerManager.actualPlayerMoved();

        playerManager.reset();

        Player player0ReRegistered = new Player(Color.BLUE, new TextRendererVisitor(new PlayerManager()));
        playerManager.register(player0ReRegistered);

        Player player1ReRegistered = new Player(Color.RED, new TextRendererVisitor(new PlayerManager()));
        playerManager.register(player1ReRegistered);

        assertThat(playerManager.actualPlayer(), is(player0ReRegistered));
    }

    @Test
    public void fourPlayerCanBeRegistered() {
        Player player0 = new Player(Color.BLUE, new TextRendererVisitor(new PlayerManager()));
        Player player1 = new Player(Color.RED, new TextRendererVisitor(new PlayerManager()));
        Player player2 = new Player(Color.GREEN, new TextRendererVisitor(new PlayerManager()));
        Player player3 = new Player(Color.YELLOW, new TextRendererVisitor(new PlayerManager()));
        playerManager.register(player0);
        playerManager.register(player1);
        playerManager.register(player2);
        playerManager.register(player3);
    }

    @Test
    public void maximumRegisteredUserCountIsFour() {
        expectedException.expect(TooManyRegisteredPlayer.class);
        expectedException.expectMessage("Too many registered player!");

        Player player0 = new Player(Color.BLUE, new TextRendererVisitor(new PlayerManager()));
        Player player1 = new Player(Color.RED, new TextRendererVisitor(new PlayerManager()));
        Player player2 = new Player(Color.GREEN, new TextRendererVisitor(new PlayerManager()));
        Player player3 = new Player(Color.YELLOW, new TextRendererVisitor(new PlayerManager()));
        Player player4 = new Player(Color.RED_BOLD, new TextRendererVisitor(new PlayerManager()));
        playerManager.register(player0);
        playerManager.register(player1);
        playerManager.register(player2);
        playerManager.register(player3);
        playerManager.register(player4);
    }

    @Test
    public void playersMustHaveDifferentColors() {
        expectedException.expect(PlayerColorException.class);
        expectedException.expectMessage("Two player cannot have the same color!");

        Player player0 = new Player(Color.BLUE, new TextRendererVisitor(new PlayerManager()));
        Player player1 = new Player(Color.BLUE, new TextRendererVisitor(new PlayerManager()));
        playerManager.register(player0);
        playerManager.register(player1);

    }
}
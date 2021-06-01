package hu.crs.montebanana.game.player;

import hu.crs.montebanana.game.rendering.Color;
import hu.crs.montebanana.game.movement.strategy.NoOpMovementStrategy;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerTest {

    @Test
    public void removeCard() {
        Player player = new Player(Color.RED, new NoOpMovementStrategy());
        player.removeCard(4);

        assertThat(player.getCards(), Matchers.containsInAnyOrder(1, 2, 3, 5));
    }

    @Test
    public void receiveBanana() {
        Player player = new Player(Color.RED, new NoOpMovementStrategy());
        assertThat(player.getBananas(), CoreMatchers.is(0));

        player.receiveBanana();
        assertThat(player.getBananas(), CoreMatchers.is(1));
    }

    @Test
    public void getBackAllCards() {
        Player player = new Player(Color.RED, new NoOpMovementStrategy());
        player.removeCard(1);
        player.removeCard(5);
        player.getBackAllCards();

        assertThat(player.getCards(), Matchers.containsInAnyOrder(1, 2, 3, 4, 5));
    }
}
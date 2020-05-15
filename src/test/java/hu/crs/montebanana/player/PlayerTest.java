package hu.crs.montebanana.player;

import hu.crs.montebanana.movement.strategy.NoOpMovementStrategy;
import hu.crs.montebanana.rendering.Color;
import org.junit.jupiter.api.Test;

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
}
package hu.crs.montebanana.player;

import org.junit.Test;
import tool.Color;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    @Test
    public void removeCard() {
        Player player = new Player(Color.RED);
        player.removeCard(4);

        assertThat(player.getCards(), containsInAnyOrder(1, 2, 3, 5));
    }

    @Test
    public void receiveBanana() {
        Player player = new Player(Color.RED);
        assertThat(player.getBananas(), is(0));

        player.receiveBanana();
        assertThat(player.getBananas(), is(1));
    }

    @Test
    public void getBackAllCards() {
        Player player = new Player(Color.RED);
        player.removeCard(1);
        player.removeCard(5);
        player.getBackAllCards();

        assertThat(player.getCards(), containsInAnyOrder(1, 2, 3, 4, 5));
    }
}
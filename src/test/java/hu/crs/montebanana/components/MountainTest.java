package hu.crs.montebanana.components;

import hu.crs.montebanana.movement.IllegalLocationException;
import hu.crs.montebanana.movement.Movement;
import org.junit.Test;
import tool.Color;

import java.util.HashMap;
import java.util.Map;

import static hu.crs.montebanana.movement.Direction.LEFT;
import static hu.crs.montebanana.movement.Direction.RIGHT;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MountainTest {

    @Test(expected = IllegalLocationException.class)
    public void findNextEmptyLocationIllegalLeftStepFromStartingPosition() {
        Player player = new Player(Color.RED);
        Player[] players = new Player[13];
        Map<String, Integer> playerLocation = new HashMap<>();

        playerLocation.put(player.getId(), -1);
        Mountain mountain = new Mountain(players, playerLocation);

        mountain.step(player, new Movement(1, LEFT));
    }

    @Test
    public void findNextEmptyLocationLegalStepFromStartingPosition() {
        Player player = new Player(Color.RED);
        Player[] players = new Player[13];
        Map<String, Integer> playerLocation = new HashMap<>();

        playerLocation.put(player.getId(), -1);
        Mountain mountain = new Mountain(players, playerLocation);

        mountain.step(player, new Movement(2, RIGHT));
        assertThat(mountain.playerLocation.get(player.getId()), is(1));
    }
}
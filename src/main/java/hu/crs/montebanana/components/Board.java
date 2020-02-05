package hu.crs.montebanana.components;

import hu.crs.montebanana.movement.Movement;
import hu.crs.montebanana.player.Player;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Board {

    @Getter
    private final Mountain mountain;

    public void step(Player player, Movement movement) {
        mountain.step(player, movement);
    }

    public void registerPlayers(List<Player> players) {
        players.forEach(mountain::registerPlayer);

    }
}

package hu.crs.montebanana.components;

import hu.crs.montebanana.movement.Movement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Board {

    @Getter
    private final List<Player> players;
    @Getter
    private final Mountain mountain;

    void step(Player player, Movement movement) {
        mountain.step(player, movement);
    }

    void registerPlayer(Player player) {
        players.add(player);
        mountain.registerPlayer(player);
    }
}

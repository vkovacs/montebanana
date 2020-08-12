package hu.crs.montebanana.game.movement.strategy;

import hu.crs.montebanana.game.components.Board;
import hu.crs.montebanana.game.movement.Movement;
import hu.crs.montebanana.game.player.Player;

public interface MovementStrategy {
    Movement next(Board board, Player player);
}

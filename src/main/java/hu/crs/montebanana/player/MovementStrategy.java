package hu.crs.montebanana.player;

import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.movement.Movement;

public interface MovementStrategy {
    Movement next(Board board, Player player);
}

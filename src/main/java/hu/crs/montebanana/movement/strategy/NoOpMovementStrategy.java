package hu.crs.montebanana.movement.strategy;

import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.movement.Movement;
import hu.crs.montebanana.player.MovementStrategy;
import hu.crs.montebanana.player.Player;

public class NoOpMovementStrategy implements MovementStrategy {
    @Override
    public Movement next(Board board, Player player) {
        throw new UnsupportedOperationException("Mock implementation!");
    }
}

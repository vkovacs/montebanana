package hu.crs.montebanana.game.movement.strategy;

import hu.crs.montebanana.game.components.Board;
import hu.crs.montebanana.game.movement.Movement;
import hu.crs.montebanana.game.player.Player;

public class NoOpMovementStrategy implements MovementStrategy {
    @Override
    public Movement next(Board board, Player player) {
        throw new UnsupportedOperationException("Mock implementation!");
    }
}

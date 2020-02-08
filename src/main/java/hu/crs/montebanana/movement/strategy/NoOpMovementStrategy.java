package hu.crs.montebanana.movement.strategy;

import hu.crs.montebanana.movement.Movement;
import hu.crs.montebanana.player.MovementStrategy;

import java.util.Collection;

public class NoOpMovementStrategy implements MovementStrategy {
    @Override
    public Movement next(Collection<Integer> availableCards) {
        throw new UnsupportedOperationException("Mock implementation!");
    }
}

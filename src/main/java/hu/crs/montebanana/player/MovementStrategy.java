package hu.crs.montebanana.player;

import hu.crs.montebanana.movement.Movement;

import java.util.Collection;

public interface MovementStrategy {
    Movement next(Collection<Integer> availableCards);
}

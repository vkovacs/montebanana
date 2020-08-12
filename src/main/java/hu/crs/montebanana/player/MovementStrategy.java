package hu.crs.montebanana.player;

import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.movement.Movement;

import java.util.Collection;

public interface MovementStrategy {
    Movement next(Board board, Collection<Integer> availableCards);
}

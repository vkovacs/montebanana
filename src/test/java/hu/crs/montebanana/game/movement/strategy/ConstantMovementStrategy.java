package hu.crs.montebanana.game.movement.strategy;

import hu.crs.montebanana.game.components.board.Board;
import hu.crs.montebanana.game.movement.Direction;
import hu.crs.montebanana.game.movement.Movement;
import hu.crs.montebanana.game.player.Player;

public class ConstantMovementStrategy implements MovementStrategy{
    @Override
    public Movement next(Board board, Player player) {
        return new Movement(1, Direction.RIGHT);
    }
}

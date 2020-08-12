package hu.crs.montebanana.movement.strategy;

import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.movement.Direction;
import hu.crs.montebanana.movement.Movement;
import hu.crs.montebanana.player.MovementStrategy;
import hu.crs.montebanana.player.Player;

import java.util.concurrent.ThreadLocalRandom;
//TODO: do not render movement error in case randomly chosen movement results in an incorrect position
public class RandomMovementStrategy implements MovementStrategy {

    @Override
    //TODO: use board to calculate steps
    public Movement next(Board board, Player player) {
        int stepCount = player.getCards().stream()
                .skip((int) (player.getCards().size() * Math.random()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Cannot generate random step!"));

        Direction direction = ThreadLocalRandom.current().nextBoolean() ? Direction.RIGHT : Direction.LEFT;

        return new Movement(stepCount, direction);
    }
}

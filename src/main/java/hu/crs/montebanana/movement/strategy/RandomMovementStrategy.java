package hu.crs.montebanana.movement.strategy;

import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.movement.Direction;
import hu.crs.montebanana.movement.Movement;
import hu.crs.montebanana.player.MovementStrategy;
import hu.crs.montebanana.player.Player;

import java.util.concurrent.ThreadLocalRandom;
public class RandomMovementStrategy implements MovementStrategy {

    @Override
    public Movement next(Board board, Player player) {
        Movement movement;
        do {
            int stepCount = player.getCards().stream()
                    .skip((int) (player.getCards().size() * Math.random()))
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Cannot generate random step!"));

            Direction direction = ThreadLocalRandom.current().nextBoolean() ? Direction.RIGHT : Direction.LEFT;

            movement = new Movement(stepCount, direction);

        } while(!board.isValidMovement(player, movement));

        return movement;
    }
}

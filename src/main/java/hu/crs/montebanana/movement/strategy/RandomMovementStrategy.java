package hu.crs.montebanana.movement.strategy;

import hu.crs.montebanana.movement.Direction;
import hu.crs.montebanana.movement.Movement;
import hu.crs.montebanana.player.MovementStrategy;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class RandomMovementStrategy implements MovementStrategy {

    @Override
    public Movement next(Collection<Integer> availableCards) {
        int card = availableCards.stream()
                .skip((int) (availableCards.size() * Math.random()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Cannot generate random step!"));


        Direction direction;
        if (ThreadLocalRandom.current().nextBoolean()) {
            direction = Direction.RIGHT;
        } else {
            direction = Direction.LEFT;
        }
        return new Movement(card, direction);
    }
}

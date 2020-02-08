package hu.crs.montebanana.movement.strategy;

import hu.crs.montebanana.movement.Direction;
import hu.crs.montebanana.movement.Movement;
import hu.crs.montebanana.player.MovementStrategy;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class RandomMovementStrategy implements MovementStrategy {

    @Override
    public Movement next(Collection<Integer> availableCards) {
        int stepCount = availableCards.stream()
                .skip((int) (availableCards.size() * Math.random()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Cannot generate random step!"));

        Direction direction = ThreadLocalRandom.current().nextBoolean() ? Direction.RIGHT : Direction.LEFT;

        return new Movement(stepCount, direction);
    }
}

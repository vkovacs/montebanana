package hu.crs.montebanana.movement;

import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class Movement {
    private int card;
    private Direction direction;
}
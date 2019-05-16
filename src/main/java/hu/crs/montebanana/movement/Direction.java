package hu.crs.montebanana.movement;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Direction {
    LEFT, RIGHT;

    public static Direction valueOfChar(char c) {
        if ('r' == c) {
            return RIGHT;
        } else if ('l' == c) {
            return LEFT;
        }
        throw new IllegalArgumentException("Invalid Direction");
    }
}

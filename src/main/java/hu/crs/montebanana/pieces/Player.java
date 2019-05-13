package hu.crs.montebanana.pieces;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
public class Player {
    @AllArgsConstructor
    public enum Color {
        RED("\u001B[31m"), GREEN("\u001B[32m"), BLUE("\u001B[34m"), YELLOW("\u001B[33m");

        @Getter
        private String colorCode;
    }

    @Getter
    private final int id;
    @Getter
    private final Set<Integer> availableCards = new TreeSet<>(Arrays.asList(1,2,3,4,5));
    private final Color color;

    public boolean removeCard(Integer cardNumber) {
        return availableCards.remove(cardNumber);
    }

    @Override
    public String toString() {
        return color.getColorCode() + "@" + "\u001B[0m";
    }
}

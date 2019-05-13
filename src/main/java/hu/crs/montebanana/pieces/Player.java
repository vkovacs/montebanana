package hu.crs.montebanana.pieces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tool.Color;
import tool.ColorTools;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@AllArgsConstructor
public class Player {

    @Getter
    private final int id;
    @Getter
    private final Set<Integer> availableCards = new TreeSet<>(Arrays.asList(1,2,3,4,5));
    private final Color color;

    public void removeCard(Integer cardNumber) {
        availableCards.remove(cardNumber);
    }

    @Override
    public String toString() {
        return ColorTools.colorText("@", color);
    }
}

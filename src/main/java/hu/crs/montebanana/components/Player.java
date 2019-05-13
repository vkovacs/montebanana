package hu.crs.montebanana.components;

import lombok.Value;
import tool.Color;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static tool.ColorTools.colorText;

@Value
public class Player {

    private final int id;
    private final Set<Integer> availableSteps = new TreeSet<>(Arrays.asList(1,2,3,4,5));
    private final Color color;

    void removeCard(Integer cardNumber) {
        availableSteps.remove(cardNumber);
    }

    @Override
    public String toString() {
        return colorText("\3 uD83D\uDC35", color);
    }
}
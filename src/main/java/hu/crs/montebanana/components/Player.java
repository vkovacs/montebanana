package hu.crs.montebanana.components;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tool.Color;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static tool.ColorTools.colorText;

@RequiredArgsConstructor
@Getter
public class Player {

    private final int id;
    private final Set<Integer> cards = new TreeSet<>(Arrays.asList(1,2,3,4,5));
    private final Color color;
    private int bananas = 0;

    void removeCard(Integer cardNumber) {
        cards.remove(cardNumber);
    }

    public void receiveBanana() {
        ++bananas;
    }

    void reset() {
        for (int i = 1; i < 6; i++) {
            cards.add(i);
        }
    }

    public String asString() {
        //return colorText("\3 uD83D\uDC35", color);
        return colorText("@", color);
    }

    @Override
    public String toString() {
        return "Player{" +
                "color=" + color +
                ", bananas=" + bananas +
                '}';
    }
}
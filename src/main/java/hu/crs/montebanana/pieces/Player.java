package hu.crs.montebanana.pieces;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Player {
    private int id;
    private Set<Integer> availableCards = new TreeSet<>(Arrays.asList(1,2,3,4,5));

    public Player(int id) {
        this.id = id;
    }

    public boolean removeCard(Integer cardNumber) {
        return availableCards.remove(cardNumber);
    }

    public Set<Integer> getAvailableCards() {
        return availableCards;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "@";
    }
}

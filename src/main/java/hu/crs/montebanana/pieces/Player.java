package hu.crs.montebanana.pieces;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Player {
    private int location = -1;
    private Set<Integer> availableCards = new TreeSet<>(Arrays.asList(1,2,3,4,5));

    public boolean removeCard(Integer cardNumber) {
        return availableCards.remove(cardNumber);
    }

    public Set<Integer> getAvailableCards() {
        return availableCards;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "@";
    }
}

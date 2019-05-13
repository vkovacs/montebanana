package hu.crs.montebanana.pieces;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Player {
    public enum Color {
        RED("\u001B[31m"), GREEN("\u001B[32m"), BLUE("\u001B[34m"), YELLOW("\u001B[33m");

        private String colorCode;

        Color(String colorCode) {
            this.colorCode = colorCode;
        }

        public String getColorCode() {
            return colorCode;
        }
    }

    private int id;
    private Set<Integer> availableCards = new TreeSet<>(Arrays.asList(1,2,3,4,5));
    private Color color;

    public Player(int id, Color color) {
        this.id = id;
        this.color = color;
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
        return color.getColorCode() + "@" + "\u001B[0m";
    }
}

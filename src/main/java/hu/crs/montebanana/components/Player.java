package hu.crs.montebanana.components;

import hu.crs.montebanana.rendering.Renderable;
import hu.crs.montebanana.rendering.RendererVisitor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tool.Color;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class Player implements Renderable {

    private final String id = UUID.randomUUID().toString();
    private final Set<Integer> cards = new TreeSet<>(Arrays.asList(1,2,3,4,5));
    private final Color color;
    private int bananas = 0;

    void removeCard(Integer cardNumber) {
        cards.remove(cardNumber);
    }

    public void receiveBanana() {
        ++bananas;
    }

    void getBackAllCards() {
        for (int i = 1; i < 6; i++) {
            cards.add(i);
        }
    }

    public Set<Integer> getCards() {
        return new HashSet<>(cards);
    }

    @Override
    public String toString() {
        return "Player{" +
                "color=" + color +
                ", bananas=" + bananas +
                '}';
    }

    @Override
    public String accept(RendererVisitor rendererVisitor) {
        return rendererVisitor.visitPlayer(this);
    }
}
package hu.crs.montebanana.game.player;

import hu.crs.montebanana.game.components.board.Board;
import hu.crs.montebanana.game.movement.Movement;
import hu.crs.montebanana.game.movement.strategy.MovementStrategy;
import hu.crs.montebanana.game.rendering.Color;
import hu.crs.montebanana.game.rendering.visitor.Renderable;
import hu.crs.montebanana.game.rendering.visitor.RendererVisitor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class Player implements Renderable {

    private final String id = UUID.randomUUID().toString();
    private final Set<Integer> cards = new TreeSet<>(Arrays.asList(1,2,3,4,5));
    private final Color color;
    private final MovementStrategy movementStrategy;
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

    public void step(Board board) {
        Movement movement = next(board);

        board.step(this, movement);
        cards.remove(movement.getCount());
        board.setLastPlayedCard(movement.getCount());
    }

    private Movement next(Board board) {
        return movementStrategy.next(board, this);
    }
}
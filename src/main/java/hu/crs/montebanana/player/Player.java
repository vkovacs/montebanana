package hu.crs.montebanana.player;

import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.movement.Direction;
import hu.crs.montebanana.movement.IllegalStepException;
import hu.crs.montebanana.movement.Movement;
import hu.crs.montebanana.rendering.Label;
import hu.crs.montebanana.rendering.Renderable;
import hu.crs.montebanana.rendering.RendererVisitor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import tool.Color;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import static hu.crs.montebanana.App.RENDERER_VISITOR;

@RequiredArgsConstructor
@Getter
public class Player implements Renderable {

    private final String id = UUID.randomUUID().toString();
    private final Set<Integer> cards = new TreeSet<>(Arrays.asList(1,2,3,4,5));
    private final Color color;
    private int bananas = 0;
    @Getter
    private int lastCard = -1;

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
        lastCard = -1;
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

    public void step(Board board) {
        Movement movement = next();

        if (movement.getCard() == lastCard) throw new IllegalStepException("Cannot use the same card as the previous player!");

        board.step(this, movement);
        cards.remove(movement.getCard());
        lastCard = movement.getCard();
    }

    private Movement next() {
        Scanner in = new Scanner(System.in);
        return readMovement(in);
    }

    private Movement readMovement(Scanner in) {
        int stepCount;
        Direction stepDirection;
        while (true) {
            try {
                String command = in.nextLine();
                stepCount = Math.abs(Integer.parseInt(command.split(" ")[0]));
                stepDirection = Direction.valueOfChar(Character.toLowerCase(command.split(" ")[1].charAt(0)));
                return new Movement(stepCount, stepDirection);
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println(error("Incorrect input! Try again!"));
            } catch (NoSuchElementException e) {
                System.out.println("Bye!");
                System.exit(0);
            }
        }
    }

    private String error(String message) {
        return new Label(message, Color.RED_BOLD).accept(RENDERER_VISITOR);
    }
}
package hu.crs.montebanana.game.movement.strategy;

import hu.crs.montebanana.game.components.Board;
import hu.crs.montebanana.game.movement.Direction;
import hu.crs.montebanana.game.movement.Movement;
import hu.crs.montebanana.game.player.Player;
import hu.crs.montebanana.game.rendering.Label;
import hu.crs.montebanana.game.rendering.Color;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static hu.crs.montebanana.game.MonteBanana.RENDERER_VISITOR;


public class ConsoleReaderStrategy implements MovementStrategy {
    public Movement next(Board board, Player player) {
        Scanner in = new Scanner(System.in);
        return readMovement(in, player.getCards());
    }

    private Movement readMovement(Scanner in, Collection<Integer> availableCards) {
        int stepCount;
        Direction stepDirection;
        while (true) {
            try {
                String command = in.nextLine();
                stepCount = Math.abs(Integer.parseInt(command.split(" ")[0]));
                if (!availableCards.contains(stepCount)) {
                    throw new IllegalArgumentException("Step is not available!");
                }
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

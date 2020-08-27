package hu.crs.montebanana.game.movement.strategy;

import hu.crs.montebanana.game.ExitGameException;
import hu.crs.montebanana.game.components.Board;
import hu.crs.montebanana.game.movement.Direction;
import hu.crs.montebanana.game.movement.Movement;
import hu.crs.montebanana.game.player.Player;
import hu.crs.montebanana.game.rendering.Color;
import hu.crs.montebanana.game.rendering.Label;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static hu.crs.montebanana.game.MonteBanana.RENDERER_VISITOR;


public class ConsoleReaderStrategy implements MovementStrategy {
    public Movement next(Board board, Player player) {
        Scanner in = new Scanner(System.in);
        return readMovement(in, board, player);
    }

    private Movement readMovement(Scanner in, Board board, Player player) {
        int stepCount;
        Direction stepDirection;
        while (true) {
            try {
                String command = in.nextLine();
                stepCount = Math.abs(Integer.parseInt(command.split(" ")[0]));
                stepDirection = Direction.valueOfChar(Character.toLowerCase(command.split(" ")[1].charAt(0)));


                Movement movement = new Movement(stepCount, stepDirection);
                boolean validMovement = board.isValidMovement(player, movement);
                if (validMovement) {
                    return movement;
                }

            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println(error("Incorrect input! Try again!"));
            } catch (NoSuchElementException e) {
                throw new ExitGameException();
            }
        }
    }

    private String error(String message) {
        return new Label(message, Color.RED_BOLD).accept(RENDERER_VISITOR);
    }
}

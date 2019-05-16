package hu.crs.montebanana;

import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.components.Player;
import lombok.Value;
import tool.Color;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static tool.ColorTools.colorText;

public class App {
    private final Board board = new Board();

    public static void main(String[] args) {
        App app = new App();
        app.board.registerPlayer(new Player(0, Color.RED));
        app.board.registerPlayer(new Player(1, Color.BLUE));
        app.board.registerPlayer(new Player(2, Color.YELLOW));

        Player actualPlayer = app.board.actualPlayer();

        Scanner in = new Scanner(System.in);
        while (app.board.playersHaveSteps()) {
            System.out.println(app.board.toString());

            MovementInput movementInput = app.readMovementInput(in);
            try {
                if (movementInput.getStepDirection() == 'r') {
                    app.board.stepRight(actualPlayer, movementInput.stepCount);
                } else {
                    app.board.stepLeft(actualPlayer, movementInput.getStepCount());
                }
            } catch (Exception e) {
                System.out.println(error(e.getMessage()));
                System.out.println();
                in.nextLine();
                continue;
            }

            colorText("", Color.RESET);
            actualPlayer = app.board.nextPlayer();
        }

        System.out.println(app.board.toString());

        System.out.println(app.winnerToString());
    }

    private MovementInput readMovementInput(Scanner in) {
        try {
            String command = in.nextLine();
            int stepCount = Math.abs(Integer.parseInt(command.split(" ")[0]));
            char stepDirection = Character.toLowerCase(command.split(" ")[1].charAt(0));

            return new MovementInput(stepCount, stepDirection);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(error("Incorrect input! Try again!"));
            readMovementInput(in);
        } catch (NoSuchElementException e) {
            System.out.println("Bye!");
            System.exit(0);
        }

        throw new RuntimeException("Cannot be reached!");
    }

    private static String error(String message) {
        return colorText(message, Color.RED_BOLD);
    }

    @Value
    private static class MovementInput {
        private int stepCount;
        private char stepDirection;
    }

    private String winnerToString() {
        Player winner = board.winner();
        return colorText("The winner is: " + winner.toString(), winner.getColor());
    }
}

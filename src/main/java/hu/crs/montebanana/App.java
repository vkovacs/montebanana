package hu.crs.montebanana;

import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.components.Player;
import hu.crs.montebanana.movement.Direction;
import lombok.Getter;
import lombok.Value;
import tool.Color;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static hu.crs.montebanana.movement.Direction.RIGHT;
import static java.lang.String.format;
import static tool.ColorTools.colorText;

public class App {
    private final Board board = new Board();

    public static void main(String[] args) {
        App app = new App();
        app.board.registerPlayer(new Player(0, Color.RED));
        app.board.registerPlayer(new Player(1, Color.BLUE));

        Player actualPlayer = app.board.actualPlayer();

        Scanner in = new Scanner(System.in);
        while (true) {
            app.board.reset();
            while (app.board.playersHaveSteps()) {
                System.out.println(app.board.asString());

                MovementInput movementInput = app.readMovementInput(in);
                try {
                    if (movementInput.getDirection() == RIGHT) {
                        app.board.stepRight(actualPlayer, movementInput.getCard());
                    } else {
                        app.board.stepLeft(actualPlayer, movementInput.getCard());
                    }
                } catch (Exception e) {
                    System.out.println(error(e.getMessage()));
                    System.out.println();
                    continue;
                }

                colorText("", Color.RESET);
                actualPlayer = app.board.nextPlayer();
            }

            System.out.println(app.board.asString());
            Player winner = app.board.winner();
            winner.win();
            System.out.println(app.winnerToString(winner));
            System.out.println(app.board.getPlayers());
        }
    }

    private MovementInput readMovementInput(Scanner in) {
        int stepCount;
        Direction stepDirection;
        while (true) {
            try {
                String command = in.nextLine();
                stepCount = Math.abs(Integer.parseInt(command.split(" ")[0]));
                stepDirection = Direction.valueOfChar(Character.toLowerCase(command.split(" ")[1].charAt(0)));
                return new MovementInput(stepCount, stepDirection);
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println(error("Incorrect input! Try again!"));
            } catch (NoSuchElementException e) {
                System.out.println("Bye!");
                System.exit(0);
            }
        }
    }

    private static String error(String message) {
        return colorText(message, Color.RED_BOLD);
    }

    private String winnerToString(Player winner) {
        return colorText(format("The winner is: %s bananas: %s!", winner.asString(), winner.getBananas()), winner.getColor());
    }

    @Value
    @Getter
    private static class MovementInput {
        private int card;
        private Direction direction;
    }
}

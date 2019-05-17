package hu.crs.montebanana;

import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.components.Mountain;
import hu.crs.montebanana.components.Player;
import hu.crs.montebanana.movement.Direction;
import hu.crs.montebanana.movement.Movement;
import tool.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.String.format;
import static tool.ColorTools.colorText;

public class App {

    private Board board;

    public static void main(String[] args) {
        App app = new App();
        app.configuration();
        app.board.registerPlayer(new Player(0, Color.RED));
        app.board.registerPlayer(new Player(1, Color.BLUE));

        Player actualPlayer = app.board.actualPlayer();

        Scanner in = new Scanner(System.in);
        while (true) {
            app.board.newTurn();
            while (app.board.playersHaveSteps()) {
                System.out.println(app.board.asString());

                Movement movement = app.readMovement(in);
                try {
                    app.board.step(actualPlayer, movement);
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
            winner.receiveBanana();
            System.out.println(app.winnerToString(winner));
            System.out.println(app.board.getPlayers());
        }
    }

    private void configuration() {
        Mountain mountain = new Mountain(new Player[13], new HashMap<>());
        board = new Board(new ArrayList<>(), mountain);
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

    private static String error(String message) {
        return colorText(message, Color.RED_BOLD);
    }

    private String winnerToString(Player winner) {
        return colorText(format("The winner is: %s bananas: %s!", winner.asString(), winner.getBananas()), winner.getColor());
    }
}

package hu.crs.montebanana;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.components.Player;
import hu.crs.montebanana.movement.Direction;
import hu.crs.montebanana.movement.Movement;
import tool.Color;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.String.format;
import static tool.ColorTools.colorText;

public class App {

    private Game game = new Game();

    public static void main(String[] args) {
        Game game = new App().game;

        game.registerPlayer(new Player(0, Color.RED));
        game.registerPlayer(new Player(1, Color.BLUE));

        Player actualPlayer = game.actualPlayer();

        Scanner in = new Scanner(System.in);
        while (true) {
            game.newTurn();
            while (game.playersHaveSteps()) {
                System.out.println(game.render());

                Movement movement = new App().readMovement(in);
                try {
                    game.step(actualPlayer, movement);
                } catch (Exception e) {
                    System.out.println(error(e.getMessage()));
                    System.out.println();
                    continue;
                }

                colorText("", Color.RESET);
                actualPlayer = game.nextPlayer();
            }

            System.out.println(game.render());
            Player winner = game.winner();
            winner.receiveBanana();
            System.out.println(new App().winnerToString(winner));
            System.out.println(game.getPlayers());
        }
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

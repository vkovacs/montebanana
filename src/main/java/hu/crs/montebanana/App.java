package hu.crs.montebanana;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.components.Player;
import hu.crs.montebanana.movement.Direction;
import hu.crs.montebanana.movement.Movement;
import hu.crs.montebanana.rendering.ColoredTextRendererVisitor;
import hu.crs.montebanana.rendering.Label;
import hu.crs.montebanana.rendering.RendererVisitor;
import hu.crs.montebanana.rendering.TextRendererVisitor;
import tool.Color;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.String.format;

public class App {

    private Game game = new Game();
    public static RendererVisitor rendererVisitor = new ColoredTextRendererVisitor(new TextRendererVisitor());

    public static void main(String[] args) {
        Game game = new App().game;

        game.registerPlayer(new Player(Color.RED));
        game.registerPlayer(new Player(Color.BLUE));

        Player actualPlayer = game.actualPlayer();

        Scanner in = new Scanner(System.in);
        while (true) {
            game.newTurn();
            while (game.playersHaveSteps()) {
                System.out.println(game.accept(rendererVisitor));
                System.out.println(game.getBoard().getMountain().accept(rendererVisitor));

                Movement movement = new App().readMovement(in);
                try {
                    game.step(actualPlayer, movement);
                } catch (Exception e) {
                    System.out.println(error(e.getMessage()));
                    System.out.println();
                    continue;
                }

                new Label("", Color.RESET).accept(rendererVisitor);
                actualPlayer = game.nextPlayer();
            }

            System.out.println(game.accept(rendererVisitor));
            System.out.println(game.getBoard().getMountain().accept(rendererVisitor));
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
        return new Label(message, Color.RED_BOLD).accept(rendererVisitor);
    }

    private String winnerToString(Player winner) {
        return new Label(format("The winner is: %s bananas: %s!", winner.accept(rendererVisitor), winner.getBananas()), winner.getColor()).accept(rendererVisitor);
    }
}

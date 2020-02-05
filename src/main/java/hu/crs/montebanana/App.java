package hu.crs.montebanana;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.player.Player;
import hu.crs.montebanana.movement.Direction;
import hu.crs.montebanana.movement.Movement;
import hu.crs.montebanana.player.PlayerManager;
import hu.crs.montebanana.rendering.ColoredTextRendererVisitor;
import hu.crs.montebanana.rendering.Label;
import hu.crs.montebanana.rendering.RendererVisitor;
import hu.crs.montebanana.rendering.TextRendererVisitor;
import tool.Color;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.String.format;

public class App {

    private PlayerManager playerManager = new PlayerManager();
    public final RendererVisitor rendererVisitor = new ColoredTextRendererVisitor(new TextRendererVisitor(playerManager));
    private Game game = new Game();

    public static void main(String[] args) {
        App app = new App();
        PlayerManager playerManager = app.playerManager;
        RendererVisitor rendererVisitor = app.rendererVisitor;

        playerManager.register(new Player(Color.RED, rendererVisitor));
        playerManager.register(new Player(Color.BLUE, rendererVisitor));

        Game game = new App().game;
        game.registerPlayers(playerManager.getPlayers());

        Player actualPlayer = playerManager.actualPlayer();

        while (true) {
            playerManager.newTurn();

            while (playerManager.playersHaveSteps()) {
                System.out.println(game.accept(rendererVisitor));
                System.out.println(game.getBoard().getMountain().accept(rendererVisitor));

                try {
                    actualPlayer.step(game.getBoard());
                } catch (Exception e) {
                    System.out.println(app.error(e.getMessage()));
                    System.out.println();
                    continue;
                }

                new Label("", Color.RESET).accept(rendererVisitor);
                playerManager.actualPlayerMoved();
                actualPlayer = playerManager.actualPlayer();
            }

            System.out.println(game.accept(rendererVisitor));
            System.out.println(game.getBoard().getMountain().accept(rendererVisitor));
            String winnerId = game.winnerId();
            Player winner = playerManager.winnerById(winnerId);
            winner.receiveBanana();
            System.out.println(new App().winnerToString(winner));
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

    private String error(String message) {
        return new Label(message, Color.RED_BOLD).accept(rendererVisitor);
    }

    private String winnerToString(Player winner) {
        return new Label(format("The winner is: %s bananas: %s!", winner.accept(rendererVisitor), winner.getBananas()), winner.getColor()).accept(rendererVisitor);
    }
}

package hu.crs.montebanana;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.player.Player;
import hu.crs.montebanana.rendering.ColoredTextRendererVisitor;
import hu.crs.montebanana.rendering.Label;
import hu.crs.montebanana.rendering.RendererVisitor;
import tool.Color;

import static java.lang.String.format;

public class App {

    public static final RendererVisitor RENDERER_VISITOR = new ColoredTextRendererVisitor();
    private Game game = new Game();

    public static void main(String[] args) {
        App app = new App();
        Game game = app.game;

        game.registerPlayer(new Player(Color.RED));
        game.registerPlayer(new Player(Color.BLUE));

        Player actualPlayer = game.actualPlayer();

        while (true) {
            game.newTurn();

            while (game.playersHaveSteps()) {
                System.out.println(game.accept(RENDERER_VISITOR));
                System.out.println(game.getBoard().getMountain().accept(RENDERER_VISITOR));

                try {
                    actualPlayer.step(game.getBoard());
                } catch (Exception e) {
                    System.out.println(app.error(e.getMessage()));
                    System.out.println();
                    continue;
                }

                new Label("", Color.RESET).accept(RENDERER_VISITOR);
                game.actualPlayerMoved();
                actualPlayer = game.actualPlayer();
            }

            System.out.println(game.accept(RENDERER_VISITOR));
            System.out.println(game.getBoard().getMountain().accept(RENDERER_VISITOR));
            Player winner = game.winner();
            winner.receiveBanana();
            System.out.println(new App().winnerToString(winner));
        }
    }

    private String error(String message) {
        return new Label(message, Color.RED_BOLD).accept(RENDERER_VISITOR);
    }

    private String winnerToString(Player winner) {
        return new Label(format("The winner is: %s bananas: %s!", winner.accept(RENDERER_VISITOR), winner.getBananas()), winner.getColor()).accept(RENDERER_VISITOR);
    }
}

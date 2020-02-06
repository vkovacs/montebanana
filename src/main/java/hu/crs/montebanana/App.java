package hu.crs.montebanana;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.player.Player;
import hu.crs.montebanana.rendering.ColoredTextRendererVisitor;
import hu.crs.montebanana.rendering.Label;
import hu.crs.montebanana.rendering.Renderable;
import hu.crs.montebanana.rendering.RendererVisitor;
import tool.Color;

import static java.lang.String.format;

public class App {

    public static final RendererVisitor RENDERER_VISITOR = new ColoredTextRendererVisitor();
    private Game game = new Game();

    public static void main(String[] args) {
        new App().start();
    }

    private void start() {
        game.registerPlayer(new Player(Color.RED));
        game.registerPlayer(new Player(Color.BLUE));

        Player actualPlayer = game.actualPlayer();

        while (true) {
            game.newTurn();

            while (game.playersHaveSteps()) {
                render(game);
                render(game.getBoard().getMountain());

                try {
                    actualPlayer.step(game.getBoard());
                } catch (Exception e) {
                    render(error(e.getMessage()));
                    System.out.println();
                    continue;
                }

                render(new Label("", Color.RESET));
                game.actualPlayerMoved();
                actualPlayer = game.actualPlayer();
            }

            render(game);
            render(game.getBoard().getMountain());
            Player winner = game.winner();
            winner.receiveBanana();
            render(winnerLabel(winner));
        }
    }

    private static Label error(String message) {
        return new Label(message, Color.RED_BOLD);
    }

    private static Label winnerLabel(Player winner) {
        return new Label(format("The winner is: %s bananas: %s!", winner.accept(RENDERER_VISITOR), winner.getBananas()), winner.getColor());
    }

    private static void render(Renderable renderable) {
        System.out.println(renderable.accept(RENDERER_VISITOR));
    }
}

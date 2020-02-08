package hu.crs.montebanana;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.movement.strategy.ConsoleReaderStrategy;
import hu.crs.montebanana.movement.strategy.RandomMovementStrategy;
import hu.crs.montebanana.player.Player;
import hu.crs.montebanana.rendering.ColoredTextRendererVisitor;
import hu.crs.montebanana.rendering.RendererVisitor;
import hu.crs.montebanana.rendering.Color;

public class App {

    public static final RendererVisitor RENDERER_VISITOR = new ColoredTextRendererVisitor();
    private Game game = new Game();

    public static void main(String[] args) {
        App app = new App();
        app.init();
        app.start();
    }

    private void init() {
        game.registerPlayer(new Player(Color.RED, new ConsoleReaderStrategy()));
        game.registerPlayer(new Player(Color.BLUE, new RandomMovementStrategy()));
    }

    private void start() {
        game.start();
    }
}

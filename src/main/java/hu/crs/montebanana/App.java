package hu.crs.montebanana;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.player.Player;
import hu.crs.montebanana.rendering.ColoredTextRendererVisitor;
import hu.crs.montebanana.rendering.RendererVisitor;
import tool.Color;

public class App {

    public static final RendererVisitor RENDERER_VISITOR = new ColoredTextRendererVisitor();
    private Game game = new Game();

    public static void main(String[] args) {
        App app = new App();
        app.init();
        app.start();
    }

    private void init() {
        game.registerPlayer(new Player(Color.RED));
        game.registerPlayer(new Player(Color.BLUE));
    }

    private void start() {
        game.start();
    }
}

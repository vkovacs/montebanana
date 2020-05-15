package hu.crs.montebanana;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.movement.strategy.ConsoleReaderStrategy;
import hu.crs.montebanana.movement.strategy.RandomMovementStrategy;
import hu.crs.montebanana.player.Player;
import hu.crs.montebanana.rendering.Color;
import hu.crs.montebanana.rendering.ColoredTextRendererVisitor;
import hu.crs.montebanana.rendering.RendererVisitor;
import org.springframework.stereotype.Component;

@Component
public class MonteBanana {
    public static final RendererVisitor RENDERER_VISITOR = new ColoredTextRendererVisitor();
    private final Game game = new Game();

    public void go() {
        init();
        start();
    }

    private void init() {
        game.registerPlayer(new Player(Color.RED, new ConsoleReaderStrategy()));
        game.registerPlayer(new Player(Color.BLUE, new RandomMovementStrategy()));
    }

    private void start() {
        game.start();
    }
}

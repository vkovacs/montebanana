package hu.crs.montebanana.game;

import hu.crs.montebanana.game.components.Game;
import hu.crs.montebanana.game.movement.strategy.ConsoleReaderStrategy;
import hu.crs.montebanana.game.movement.strategy.RandomMovementStrategy;
import hu.crs.montebanana.game.player.Player;
import hu.crs.montebanana.game.rendering.Color;
import hu.crs.montebanana.game.rendering.visitor.ColoredTextRendererVisitor;
import hu.crs.montebanana.game.rendering.visitor.RendererVisitor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonteBanana {
    //TODO: make a spring bean
    public static final RendererVisitor RENDERER_VISITOR = new ColoredTextRendererVisitor();
    private final Game game;

    public void start() {
        game.registerPlayer(new Player(Color.RED, new ConsoleReaderStrategy()));
        game.registerPlayer(new Player(Color.BLUE, new RandomMovementStrategy()));
        game.start();
    }
}

package hu.crs.montebanana.game;

import hu.crs.montebanana.game.components.Game;
import hu.crs.montebanana.game.player.Player;
import hu.crs.montebanana.game.rendering.Color;
import hu.crs.montebanana.game.rendering.visitor.RendererVisitor;
import hu.crs.montebanana.game.movement.strategy.ConsoleReaderStrategy;
import hu.crs.montebanana.game.movement.strategy.RandomMovementStrategy;
import hu.crs.montebanana.game.rendering.visitor.ColoredTextRendererVisitor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
@RequiredArgsConstructor
public class MonteBanana {
    public static final RendererVisitor RENDERER_VISITOR = new ColoredTextRendererVisitor();
    private final Game game;

    public void start() {
        game.registerPlayer(new Player(Color.YELLOW, new ConsoleReaderStrategy()));
        game.registerPlayer(new Player(Color.BLUE, new RandomMovementStrategy()));
        try {
            game.start();
        } catch (ExitGameException e) {
            game.getPlayerManager().getPlayers()
                    .forEach(p -> {
                        System.out.println(RENDERER_VISITOR.visitPlayer(p) + " bananas: " + p.getBananas());
                    });
            System.out.println("Winner of the hu.crs.montabanana.hu.crs.montebanana.game: " + RENDERER_VISITOR.visitPlayer(
                    game.getPlayerManager()
                            .getPlayers()
                            .stream()
                            .max(Comparator.comparingInt(Player::getBananas)
                            ).orElseThrow()));
        }
    }
}

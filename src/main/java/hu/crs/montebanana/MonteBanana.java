package hu.crs.montebanana;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.movement.strategy.ConsoleReaderStrategy;
import hu.crs.montebanana.movement.strategy.RandomMovementStrategy;
import hu.crs.montebanana.player.Player;
import hu.crs.montebanana.rendering.Color;
import hu.crs.montebanana.rendering.ColoredTextRendererVisitor;
import hu.crs.montebanana.rendering.RendererVisitor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonteBanana implements ApplicationRunner {

    public static final RendererVisitor RENDERER_VISITOR = new ColoredTextRendererVisitor();
    private final Game game = new Game();

    public static void main(String[] args) {
        SpringApplication.run(MonteBanana.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MonteBanana monteBanana = new MonteBanana();
        monteBanana.init();
        monteBanana.start();
    }

    private void init() {
        game.registerPlayer(new Player(Color.RED, new ConsoleReaderStrategy()));
        game.registerPlayer(new Player(Color.BLUE, new RandomMovementStrategy()));
    }

    private void start() {
        game.start();
    }
}

package hu.crs.montebanana.components;

import hu.crs.montebanana.player.Player;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class BoardConfiguration {
    @Bean
    public Board board() {
        return new Board(new Player[13], new HashMap<>());
    }
}

package hu.crs.montebanana;

import hu.crs.montebanana.game.MonteBanana;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RequiredArgsConstructor
@ComponentScan("hu.crs.montebanana.game")
public class App implements ApplicationRunner {
    private final MonteBanana monteBanana;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        monteBanana.start();
    }
}

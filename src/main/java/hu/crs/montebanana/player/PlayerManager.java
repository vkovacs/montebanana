package hu.crs.montebanana.player;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    @Getter
    List<Player> players = new ArrayList<>();
    private int actualPlayer = -1;

    public void register(Player player) {
        if (players.size() >= 4) {
            throw new TooManyRegisteredPlayer("Too many registered player!");
        }

        if (players.stream()
                .anyMatch(p -> p.getColor() == player.getColor())) {
            throw new PlayerColorException("Two player cannot have the same color!");

        }

        players.add(player);
        if (actualPlayer < 0) {
            actualPlayer = 0;
        }
    }

    public Player actualPlayer() {
        if (players.isEmpty()) {
            throw new NoSuchPlayerException("No player is registered!");
        }

        return players.get(actualPlayer);
    }

    public void actualPlayerMoved() {
        if (players.isEmpty()) {
            throw new NoSuchPlayerException("No player is registered!");
        }

        if (++actualPlayer >= players.size()) {
            actualPlayer = 0;
        }
    }

    public void newTurn() {
        players.forEach(Player::getBackAllCards);
    }

    public boolean playersHaveSteps() {
        return players.stream().map(player -> player.getCards().size()).reduce(0, Integer::sum) > 0;
    }

    public void reset() {
        players.clear();
        actualPlayer = -1;
    }

    public Player winnerById(String winnerId) {
        return players.stream().filter(p -> p.getId().equals(winnerId)).findFirst().orElseThrow(() -> new RuntimeException("No player by id!"));
    }
}

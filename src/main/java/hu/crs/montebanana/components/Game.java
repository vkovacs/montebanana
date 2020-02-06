package hu.crs.montebanana.components;

import hu.crs.montebanana.player.Player;
import hu.crs.montebanana.player.PlayerManager;
import hu.crs.montebanana.rendering.Renderable;
import hu.crs.montebanana.rendering.RendererVisitor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

public class Game implements Renderable {

    @Getter
    private Board board;

    @Getter
    private PlayerManager playerManager = new PlayerManager();

    public Game() {
        Mountain mountain = new Mountain(new Player[13], new HashMap<>());
        board = new Board(mountain);
    }


    public String winnerId() {
        return board.getMountain().winnerId();
    }

    @Override
    public String accept(RendererVisitor rendererVisitor) {
        return rendererVisitor.visitGame(this);
    }

    public void registerPlayers(List<Player> players) {
        board.registerPlayers(players);
    }
}

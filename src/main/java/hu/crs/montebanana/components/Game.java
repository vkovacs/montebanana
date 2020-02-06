package hu.crs.montebanana.components;

import hu.crs.montebanana.player.Player;
import hu.crs.montebanana.player.PlayerManager;
import hu.crs.montebanana.rendering.Renderable;
import hu.crs.montebanana.rendering.RendererVisitor;
import lombok.Getter;

import java.util.HashMap;

public class Game implements Renderable {
    @Getter
    private Board board;
    private PlayerManager playerManager = new PlayerManager();

    public Game() {
        Mountain mountain = new Mountain(new Player[13], new HashMap<>());
        board = new Board(mountain);
    }

    @Override
    public String accept(RendererVisitor rendererVisitor) {
        return rendererVisitor.visitGame(this);
    }

    public void registerPlayer(Player player) {
        playerManager.register(player);
        board.registerPlayer(player);
    }

    public Player actualPlayer() {
        return playerManager.actualPlayer();
    }

    public void newTurn() {
        playerManager.newTurn();
    }

    public boolean playersHaveSteps() {
        return playerManager.playersHaveSteps();
    }

    public void actualPlayerMoved() {
        playerManager.actualPlayerMoved();
    }

    public Player winner() {
        return playerManager.winnerById(winnerId());
    }

    public String winnerId() {
        return board.getMountain().winnerId();
    }
}

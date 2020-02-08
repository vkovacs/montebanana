package hu.crs.montebanana.components;

import hu.crs.montebanana.player.Player;
import hu.crs.montebanana.player.PlayerManager;
import hu.crs.montebanana.rendering.Label;
import hu.crs.montebanana.rendering.Renderable;
import hu.crs.montebanana.rendering.RendererVisitor;
import lombok.Getter;
import tool.Color;

import java.util.HashMap;

import static hu.crs.montebanana.App.RENDERER_VISITOR;
import static java.lang.String.format;

public class Game implements Renderable {
    @Getter
    private Board board = new Board(new Player[13], new HashMap<>());
    private PlayerManager playerManager = new PlayerManager();

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        Player actualPlayer = actualPlayer();

        while (true) {
            newTurn();

            while (playersHaveSteps()) {
                render(this);

                try {
                    actualPlayer.step(board);
                } catch (Exception e) {
                    render(error(e.getMessage()));
                    System.out.println();
                    continue;
                }

                render(new Label("", Color.RESET));
                actualPlayerMoved();
                actualPlayer = actualPlayer();
            }

            render(this);

            Player winner = determineAndHandleWinner();
            render(winnerLabel(winner));
            board.reset();
        }
    }

    private Player determineAndHandleWinner() {
        Player winner = playerManager.winnerById(board.winnerId());
        winner.receiveBanana();
        return winner;
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

    private static Label error(String message) {
        return new Label(message, Color.RED_BOLD);
    }

    private static Label winnerLabel(Player winner) {
        return new Label(format("The winner is: %s bananas: %s!", winner.accept(RENDERER_VISITOR), winner.getBananas()), winner.getColor());
    }

    private static void render(Renderable renderable) {
        System.out.println(renderable.accept(RENDERER_VISITOR));
    }
}

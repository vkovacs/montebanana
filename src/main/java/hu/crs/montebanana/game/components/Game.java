package hu.crs.montebanana.game.components;

import hu.crs.montebanana.game.player.Player;
import hu.crs.montebanana.game.player.PlayerManager;
import hu.crs.montebanana.game.rendering.Color;
import hu.crs.montebanana.game.rendering.Label;
import hu.crs.montebanana.game.rendering.visitor.Renderable;
import hu.crs.montebanana.game.rendering.visitor.RendererVisitor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static hu.crs.montebanana.game.MonteBanana.RENDERER_VISITOR;
import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class Game implements Renderable {
    @Getter
    private final Board board;
    private final PlayerManager playerManager;
    @Getter
    private int lastPlayedCard;

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        Player actualPlayer = actualPlayer();

        while (true) {
            newTurn();

            while (playersHaveSteps()) {
                render(this);

                try {
                    lastPlayedCard = actualPlayer.step(board, lastPlayedCard);
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
            board.reset(playerManager.getPlayers());
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

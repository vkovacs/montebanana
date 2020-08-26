package hu.crs.montebanana.game.components;

import hu.crs.montebanana.game.ExitGameException;
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
    @Getter
    private final PlayerManager playerManager;
    @Getter
    private int lastPlayedCard;
    private int round;

    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        Player actualPlayer = actualPlayer();

        while (true) {
            newTurn();
            System.out.println("==========");
            System.out.println("Round: " + round);
            System.out.println("==========");

            while (playersHaveSteps()) {
                render(this);

                try {
                    lastPlayedCard = actualPlayer.step(board, lastPlayedCard);
                } catch (Exception e) {
                    if (e instanceof ExitGameException) throw e;

                    render(error(e.getMessage()));
                    System.out.println();
                    continue;
                }

                render(new Label("", Color.RESET));
                actualPlayerMoved();
                actualPlayer = actualPlayer();
            }

            render(this);

            Player winner = determineAndHandleRoundWinner();
            render(winnerLabel(winner));
        }
    }

    private Player determineAndHandleRoundWinner() {
        Player roundWinner = playerManager.winnerById(board.winnerId());
        roundWinner.receiveBanana();
        return roundWinner;
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
        board.reset(playerManager.getPlayers());
        round++;
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
        return new Label(format("The winner of the round is: %s bananas: %s!", winner.accept(RENDERER_VISITOR), winner.getBananas()), winner.getColor());
    }

    private static void render(Renderable renderable) {
        System.out.println(renderable.accept(RENDERER_VISITOR));
    }
}

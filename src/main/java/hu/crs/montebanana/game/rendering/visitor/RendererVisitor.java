package hu.crs.montebanana.game.rendering.visitor;

import hu.crs.montebanana.game.components.Game;
import hu.crs.montebanana.game.components.board.Board;
import hu.crs.montebanana.game.player.Player;
import hu.crs.montebanana.game.rendering.Label;

public interface RendererVisitor {
    String visitPlayer(Player player);

    String visitLabel(Label label);

    String visitGame(Game game);

    String visitBoard(Board board);
}

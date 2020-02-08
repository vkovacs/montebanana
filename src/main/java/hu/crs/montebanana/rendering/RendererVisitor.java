package hu.crs.montebanana.rendering;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.player.Player;

public interface RendererVisitor {
    String visitPlayer(Player player);

    String visitLabel(Label label);

    String visitGame(Game game);

    String visitBoard(Board board);
}

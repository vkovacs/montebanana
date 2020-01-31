package hu.crs.montebanana.rendering;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.components.Mountain;
import hu.crs.montebanana.components.Player;

public interface RendererVisitor {
    String visitPlayer(Player player);

    String visitLabel(Label label);

    String visitGame(Game game);

    String visitMountain(Mountain mountain);
}

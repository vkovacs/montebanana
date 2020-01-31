package hu.crs.montebanana.rendering;

import hu.crs.montebanana.components.Player;

public interface RendererVisitor {
    String visitPlayer(Player player);
}

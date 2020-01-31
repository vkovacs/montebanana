package hu.crs.montebanana.rendering;

import hu.crs.montebanana.components.Player;

public class TextRendererVisitor implements RendererVisitor {
    @Override
    public String visitPlayer(Player player) {
        return "@";
    }
}

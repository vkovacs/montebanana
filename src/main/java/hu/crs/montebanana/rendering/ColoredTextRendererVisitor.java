package hu.crs.montebanana.rendering;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.components.Mountain;
import hu.crs.montebanana.components.Player;
import tool.Color;

public class ColoredTextRendererVisitor implements RendererVisitor{
    private final TextRendererVisitor textRendererVisitor;

    public ColoredTextRendererVisitor(TextRendererVisitor textRendererVisitor) {
        this.textRendererVisitor = textRendererVisitor;
    }

    @Override
    public String visitPlayer(Player player) {
        String renderedPlayer = textRendererVisitor.visitPlayer(player);
        return colorText(renderedPlayer, player.getColor());
    }

    @Override
    public String visitLabel(Label label) {
        String renderedLabel = textRendererVisitor.visitLabel(label);
        return colorText(renderedLabel, label.getColor());
    }

    @Override
    public String visitGame(Game game) {
        String renderedGame = textRendererVisitor.visitGame(game);

        return colorText(renderedGame, game.actualPlayer().getColor());
    }

    @Override
    public String visitMountain(Mountain mountain) {
        return textRendererVisitor.visitMountain(mountain);
    }

    public static String colorText(String text, Color color) {
        return color.getColorCode() + text + Color.RESET.getColorCode();
    }
}

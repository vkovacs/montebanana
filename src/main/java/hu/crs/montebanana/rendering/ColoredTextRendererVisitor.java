package hu.crs.montebanana.rendering;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.player.Player;
import tool.Color;

public class ColoredTextRendererVisitor extends TextRendererVisitor implements RendererVisitor {

    @Override
    public String visitPlayer(Player player) {
        String renderedPlayer = super.visitPlayer(player);
        return colorText(renderedPlayer, player.getColor());
    }

    @Override
    public String visitLabel(Label label) {
        String renderedLabel = super.visitLabel(label);
        return colorText(renderedLabel, label.getColor());
    }

    @Override
    public String visitGame(Game game) {
        String renderedGame = super.visitGame(game);
        return colorText(renderedGame, game.actualPlayer().getColor());
    }

    public static String colorText(String text, Color color) {
        return color.getColorCode() + text + Color.RESET.getColorCode();
    }
}

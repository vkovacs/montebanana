package hu.crs.montebanana.rendering;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.components.Board;
import hu.crs.montebanana.player.Player;

import static java.lang.String.format;

public class TextRendererVisitor implements RendererVisitor {

    @Override
    public String visitPlayer(Player player) {
        return "@";
    }

    @Override
    public String visitLabel(Label label) {
        return label.getLabel();
    }

    @Override
    public String visitGame(Game game) {
        return format("Available steps: %s", game.actualPlayer().getCards())
                + System.lineSeparator() +
                visitBoard(game.getBoard());
    }

    @Override
    public String visitBoard(Board board) {
        StringBuilder stringBuilder = new StringBuilder(26);
        for (Player player : board.getSteps()) {
            if (player != null) stringBuilder.append(visitPlayer(player)).append(" ");
            else stringBuilder.append("  ");
        }
        String playersLine = stringBuilder.toString();
        String stepsLine = "_ _ _ _ _ _ _ _ _ _ _ _ _";
        return playersLine + System.lineSeparator() + stepsLine;
    }
}

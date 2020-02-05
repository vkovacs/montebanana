package hu.crs.montebanana.rendering;

import hu.crs.montebanana.components.Game;
import hu.crs.montebanana.components.Mountain;
import hu.crs.montebanana.player.Player;
import hu.crs.montebanana.player.PlayerManager;
import lombok.Getter;

import static java.lang.String.format;

public class TextRendererVisitor implements RendererVisitor {
    @Getter
    private PlayerManager playerManager;
    public TextRendererVisitor(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

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
        return format("Available steps: %s", playerManager.actualPlayer().getCards());
    }

    @Override
    public String visitMountain(Mountain mountain) {
        StringBuilder stringBuilder = new StringBuilder(26);
        for (Player player : mountain.getSteps()) {
            if (player != null) stringBuilder.append(player.accept(this)).append(" ");
            else stringBuilder.append("  ");
        }
        String playersLine = stringBuilder.toString();
        String stepsLine = "_ _ _ _ _ _ _ _ _ _ _ _ _";
        return playersLine + System.lineSeparator() + stepsLine;
    }
}

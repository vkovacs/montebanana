package hu.crs.montebanana.game.rendering;

import hu.crs.montebanana.game.rendering.visitor.Renderable;
import hu.crs.montebanana.game.rendering.visitor.RendererVisitor;

public class Label implements Renderable {
    private final String label;
    private final Color color;

    public Label(String label, Color color) {
        this.label = label;
        this.color = color;
    }

    @Override
    public String accept(RendererVisitor rendererVisitor) {
        return rendererVisitor.visitLabel(this);
    }

    public String getLabel() {
        return label;
    }

    public Color getColor() {
        return color;
    }
}

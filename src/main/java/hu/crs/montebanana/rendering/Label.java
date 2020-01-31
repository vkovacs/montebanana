package hu.crs.montebanana.rendering;

import tool.Color;

public class Label implements Renderable{
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

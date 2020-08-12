package hu.crs.montebanana.game.rendering.visitor;

public interface Renderable {
    String accept(RendererVisitor rendererVisitor);
}

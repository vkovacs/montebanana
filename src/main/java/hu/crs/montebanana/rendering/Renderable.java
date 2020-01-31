package hu.crs.montebanana.rendering;

public interface Renderable {
    String accept(RendererVisitor rendererVisitor);
}

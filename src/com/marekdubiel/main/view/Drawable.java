package com.marekdubiel.main.view;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable {
    public void render(GraphicsContext graphicsContext);
    public boolean isNeeded();
    public void setNeeded(boolean needed);
}

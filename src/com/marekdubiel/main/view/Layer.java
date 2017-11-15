package com.marekdubiel.main.view;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Layer {
    private volatile List<Drawable> drawables;

    public Layer(){
        drawables = Collections.synchronizedList(new ArrayList());
    }

    public void drawSprites(GraphicsContext graphicsContext){
        if(drawables!=null)
            drawables.removeIf(drawable -> !drawable.isNeeded());
        drawables.forEach(drawable -> drawable.render(graphicsContext));
    }

    public void addDrawable(Drawable drawable){
        drawable.setNeeded(true);
        drawables.add(drawable);
    }
}

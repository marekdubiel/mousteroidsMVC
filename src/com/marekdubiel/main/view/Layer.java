package com.marekdubiel.main.view;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Layer {
    private volatile List<Sprite> sprites;

    public Layer(){
        sprites = Collections.synchronizedList(new ArrayList());
    }

    public void drawSprites(GraphicsContext graphicsContext){
        if(sprites!=null)
            sprites.removeIf(sprite -> !sprite.isNeeded());
        sprites.forEach(sprite -> sprite.render(graphicsContext));
    }

    public void addSprite(Sprite sprite){
        sprite.setNeeded(true);
        sprites.add(sprite);
    }
}

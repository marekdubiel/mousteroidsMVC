package com.marekdubiel.main.view;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Sprite> sprites;

    public Layer(){
        sprites = new ArrayList<>();
    }

    public void drawSprites(GraphicsContext graphicsContext){
        if(sprites!=null)
            sprites.removeIf(sprite -> sprite.getNeeded()==false);
        sprites.forEach(sprite -> sprite.render(graphicsContext));
    }

    public void addSprite(Sprite sprite){
        sprite.setNeeded(true);
        sprites.add(sprite);
    }
}

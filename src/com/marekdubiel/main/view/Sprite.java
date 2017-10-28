package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.model.GameObject;
import javafx.scene.canvas.GraphicsContext;

public abstract class Sprite {
    GameObject parent;
    Double2D position;
    double rotation;
    double scale;
    boolean needed;

    public Sprite(GameObject object){
        this.parent = object;
    }

    abstract void render(GraphicsContext graphicsContext);

    public void update(){
        if(parent!=null) {
            setNeeded(parent.getAlive());
            position = parent.getPosition();
            rotation = parent.getRotation();
            scale = parent.getScale();
        }else{
            setNeeded(false);
        }
    }
    public void setNeeded(boolean needed){
        this.needed = needed;
    }

    public boolean getNeeded(){
        return needed;
    }
}

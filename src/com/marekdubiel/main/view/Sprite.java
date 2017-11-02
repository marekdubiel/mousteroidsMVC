package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.model.CollidableObject;
import com.marekdubiel.main.model.SimpleObject;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Sprite {
    SimpleObject parent;
    Double2D position;
    double rotation;
    double scale;
    boolean needed;
    int layer;

    public Sprite(SimpleObject object, int layer){
        this.parent = object;
        setLayer(layer);
    }

    abstract void render(GraphicsContext graphicsContext);

    public abstract ArrayList<Double2D> getBounds();

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

    public void setLayer(int layer){
        this.layer = Math.min(9,Math.max(0,layer));
    }

    public int getLayer(){
        return layer;
    }
}

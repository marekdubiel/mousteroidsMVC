package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.model.CollidableObject;
import com.marekdubiel.main.model.SimpleObject;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Sprite {
    private SimpleObject parent;
    private Double2D position;
    private double rotation;
    private double scale;
    private boolean needed;
    private boolean visible;

    int layer;

    public Sprite(SimpleObject object, int layer){
        this.parent = object;
        setLayer(layer);
        setVisible(true);

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

    public boolean isNeeded(){
        return needed;
    }

    public void setLayer(int layer){
        this.layer = Math.min(9,Math.max(0,layer));
    }

    public int getLayer(){
        return layer;
    }

    public void setScale (double scale){
        this.scale = scale;
    }

    public double getScale() {
        return scale;
    }

    public void setRotation (double rotation){
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setPosition(Double2D position) {
        this.position = position;
    }

    public Double2D getPosition() {
        return position;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}

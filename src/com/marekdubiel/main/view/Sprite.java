package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.model.SimpleObject;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Sprite implements Drawable {
    private SimpleObject parent;
    private Double2D position;
    private double rotation;
    private double scale;
    private boolean needed;
    private boolean visible;

    public Sprite(SimpleObject object){
        this.parent = object;
        setVisible(true);

    }

    public void render(GraphicsContext graphicsContext){

    };

    public abstract ArrayList<Double2D> getBounds();

    public void update(){
        if(parent!=null) {
            setNeeded(parent.isAlive());
            setPosition(parent.getPosition());
            setRotation(parent.getRotation());
            setScale(parent.getScale());
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

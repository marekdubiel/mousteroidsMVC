package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.view.ImageSprite;
import com.marekdubiel.main.view.Sprite;
import com.marekdubiel.main.view.TextSprite;

public class SimpleObject {

    private Sprite sprite;
    private Double2D position;
    private double rotation;
    private double scale;
    private boolean alive;

    public SimpleObject(){
    }

    public void initializeGeneralObject(Double2D position){
        setPosition(position);
        ObjectManager.getInstance().addObject(this);
    }
    public void initializeImageObject(String shape, int layer, double rotation, double scale){
        setRotation(rotation);
        setScale(scale);
        sprite = new ImageSprite(shape,layer,this);

    }

    public void initializeTextObject(String text, double size, int layer, boolean whiteFont){
        setRotation(0);
        setScale(1);
        sprite = new TextSprite(text, size, layer, this, whiteFont);
    }

    public Sprite getSprite(){
        return sprite;
    }

    public void setPosition(Double2D position) {
        this.position = position;
    }

    public Double2D getPosition() {
        return position;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation(){
        return rotation;
    }

    public void setScale(double scale){
        this.scale = scale;
    }

    public double getScale(){
        return scale;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }

    public boolean getAlive(){
        return alive;
    }

    public void moveAhead(){

    }

    public void moveReverse(){

    }

    public void update(double delta){

    }


}

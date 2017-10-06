package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Collidable;
import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.view.Sprite;

import java.util.ArrayList;

public class GameObject implements Collidable{

    private ArrayList<Double2D> vertices;
    private Sprite sprite;
    private Double2D position;
    private double rotation;
    private double speed;
    private double maxSpeed;
    private double direction;
    private boolean alive;

    public GameObject() {

    }

    public void update(Double delta){

    }

    public void moveAhead(){

    }

    public void moveReverse(){

    }

    public void setBoundingVertices(ArrayList<Double2D> vertices){
        this.vertices = vertices;
    }

    @Override
    public ArrayList<Double2D> getBoundingVertices(){
        return vertices;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public void setPosition(Double2D position){
        this.position = position;
    }

    @Override
    public Double2D getPosition(){
        return position;
    }

    public void setRotation(double rotation){
        this.rotation = rotation;
    }

    @Override
    public double getRotation(){
        return rotation;
    }


}

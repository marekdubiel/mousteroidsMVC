package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Calculate;
import com.marekdubiel.main.additional.Collidable;
import com.marekdubiel.main.additional.Double2D;

import java.util.ArrayList;

public class CollidableObject extends SimpleObject implements Collidable{

    private ArrayList<Double2D> vertices;
    private double direction;
    private double speed;
    private double maxSpeed;

    public CollidableObject() {

    }

    public void initializeCollidableObject(String shape, int layer, Double2D position,double direction, double scale){
        super.initializeGeneralObject(position);
        super.initializeImageObject(shape,layer,direction,scale);
        setDirection(direction);
        assignBoundingBoxFromSprite();
    }

    public void update(double delta){
        super.update(delta);
        cutSpeed();
        move(delta);
    }

    private void cutSpeed(){
        setSpeed(Math.min(getSpeed(),getMaxSpeed()));
    }

    private void move(double delta){
        setPosition(Calculate.pointByDirectionAndDistance(getPosition(),getDirection(),getSpeed()*delta));
    }

    public void assignBoundingBoxFromSprite(){
        setBoundingVertices(super.getSprite().getBounds());
    }

    public void setBoundingVertices(ArrayList<Double2D> vertices){
        this.vertices = vertices;
    }

    @Override
    public ArrayList<Double2D> getBoundingVertices(){
        return vertices;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void moveInDirection(double direction, double distance){
        setPosition(Calculate.pointByDirectionAndDistance(getPosition(),direction,distance));
    }
}

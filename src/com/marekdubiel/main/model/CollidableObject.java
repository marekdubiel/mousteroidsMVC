package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Collidable;
import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.view.ImageSprite;
import com.marekdubiel.main.view.Sprite;

import java.util.ArrayList;

public class CollidableObject extends SimpleObject implements Collidable{

    private ArrayList<Double2D> vertices;

    public CollidableObject() {

    }

    public void update(double delta){
        super.update(delta);
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
}

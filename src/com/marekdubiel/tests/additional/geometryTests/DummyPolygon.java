package com.marekdubiel.tests.additional.geometryTests;

import com.marekdubiel.main.additional.Collidable;
import com.marekdubiel.main.additional.Double2D;

import java.util.ArrayList;

public class DummyPolygon implements Collidable {
    ArrayList<Double2D> vertices;
    Double2D position;
    double rotation;
    double scale;


    public DummyPolygon(ArrayList<Double2D> vertices, Double2D position, double rotation, double scale){
        setBoundingVertices(vertices);
        setPosition(position);
        setRotation(rotation);
        setScale(scale);
    }

    public void setBoundingVertices(ArrayList<Double2D> vertices) {
        this.vertices = vertices;
    }

    @Override
    public ArrayList<Double2D> getBoundingVertices() {
        return vertices;
    }

    public void setPosition(Double2D position) {
        this.position = position;
    }

    @Override
    public Double2D getPosition() {
        return position;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    public void setScale(double scale){
        this.scale = scale;
    }

    @Override
    public double getScale(){
        return scale;
    }
}

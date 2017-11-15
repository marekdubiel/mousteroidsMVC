package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;

public class PendingAsteroid {
    private int size;
    private Double2D position;
    private double direction;

    public PendingAsteroid(int size, Double2D position, double direction){
        this.size = size;
        this.position = position;
        this.direction = direction;
    }

    public int getSize() {
        return size;
    }

    public Double2D getPosition() {
        return position;
    }

    public double getDirection() {
        return direction;
    }
}

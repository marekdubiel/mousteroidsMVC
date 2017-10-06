package com.marekdubiel.main.additional;

import java.util.ArrayList;

public interface Collidable {
    ArrayList<Double2D> getBoundingVertices();
    Double2D getPosition();
    double getRotation();
}

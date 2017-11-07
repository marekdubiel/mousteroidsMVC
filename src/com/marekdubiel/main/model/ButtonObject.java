package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Collidable;
import com.marekdubiel.main.additional.Double2D;

import java.util.ArrayList;

public class ButtonObject  extends SimpleObject implements Collidable {

    private ArrayList<Double2D> vertices;

    public ButtonObject(){

    }

    public static ButtonObject withLabel(String communicate, Double2D offset){
        ButtonObject instance = new ButtonObject();
        instance.addCommunicate(communicate,offset);
        return instance;
    }

    private void addCommunicate(String label, Double2D offset){

    }

    @Override
    public ArrayList<Double2D> getBoundingVertices(){
        return vertices;
    }
}

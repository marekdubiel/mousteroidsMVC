package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;

public class ButtonObject {

    public ButtonObject(){

    }

    public static ButtonObject withCommunicate(String communicate, Double2D offset){
        ButtonObject instance = new ButtonObject();
        instance.addCommunicate(communicate,offset);
        return instance;
    }

    private void addCommunicate(String communicate, Double2D offset){

    }
}

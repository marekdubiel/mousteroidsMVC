package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.view.Shape;
import com.marekdubiel.main.view.Sprite;

public class PlayerObject extends GameObject{
    Sprite sprite;
    public PlayerObject(){
        initialize();
    }

    private void initialize(){
        super.setPosition(new Double2D(Settings.getInstance().getWindowWidth()/2,Settings.getInstance().getWindowHeight()/2));
        super.setRotation(90);
        super.setScale(1);
        sprite = new Sprite(this, Shape.SPACESHIP);
    }

}

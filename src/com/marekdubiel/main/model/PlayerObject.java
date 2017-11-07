package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Calculate;
import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.controller.InputManager;
import com.marekdubiel.main.view.ImageSprite;
import com.marekdubiel.main.view.Sprite;

public class PlayerObject extends CollidableObject {

    public PlayerObject(){
        initialize();
    }

    private void initialize(){
        super.initializeGeneralObject(Settings.getInstance().centerPoint());
        super.initializeImageObject("spaceship",6,90,1);
        super.assignBoundingBoxFromSprite();
    }

    public void update(double delta){
        super.update(delta);
        setRotation(Calculate.direction(InputManager.getInstance().getFocusPoint(),getPosition()));
    }

}

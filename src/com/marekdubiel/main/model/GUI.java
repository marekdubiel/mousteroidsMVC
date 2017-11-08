package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.controller.InputManager;

public class GUI {
    static GUI instance;
    private SimpleObject crosshair;
    private Score score;

    public static GUI getInstance() {
        if(instance==null)
            instance = new GUI();
        return instance;
    }

    private GUI(){

    }

    public void initialize(){
        createCrosshair();
        createScore();
    }

    private void createCrosshair(){
        crosshair = new SimpleObject(){
            public void update(double delta){
                setPosition(InputManager.getInstance().getFocusPoint());
            }
        };
        crosshair.initializeGeneralObject(InputManager.getInstance().getFocusPoint());
        crosshair.initializeImageObject("crosshair",9,0,1);
    }

    private void createScore(){
        Double2D scorePosition = Double2D.multiply(InputManager.getInstance().getFocusPoint(),new Double2D(1,0.1));
        score = new Score(scorePosition);
    }

    public void update(){
    }
}

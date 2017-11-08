package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;

public class Menu {
    private static Menu instance;
    private SimpleObject title;
    private ButtonObject startButton;

    public static Menu getInstance() {
        if(instance==null)
            instance = new Menu();
        return instance;
    }

    private Menu(){
    }

    public void start(){
        title = new SimpleObject();
        Double2D titleOffset = new Double2D(1,0.5);
        title.initializeGeneralObject(Double2D.multiply(Settings.getInstance().centerPoint(),titleOffset));
        title.initializeTextObject("mAuSTEROIDS",64,8,true);

        startButton = new ButtonObject();
        Double2D startButtonOffset = new Double2D(1,1.2);
        startButton.initializeGeneralObject(Double2D.multiply(Settings.getInstance().centerPoint(),startButtonOffset));
        startButton.initializeImageObject("asteroidBig",7,0,8);
        startButton.initializeTextObject("start",32,8,true);

    }
    public void update(double delta){
        if(startButton!=null && startButton.getButtonPressed()) {
            terminate();
            ObjectManager.getInstance().startGame();
        }
    }

    private void terminate(){
        title.setAlive(false);
        startButton.setAlive(false);
    }
}

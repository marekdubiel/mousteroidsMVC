package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;

public class Menu {
    private SimpleObject title;
    private ButtonObject startButton;
    private boolean active;

    public Menu(){
    }

    public void start(){
        createTitle();
        createButton();
        setActive(true);
    }

    private void createTitle(){
        title = new SimpleObject();
        Double2D titleOffset = new Double2D(1,0.5);
        title.initializeGeneralObject(Double2D.multiply(Settings.getInstance().centerPoint(),titleOffset));
        title.initializeTextObject("mAuSTEROIDS",64,8,true);

    }

    private void createButton(){
        startButton = new ButtonObject();
        Double2D startButtonOffset = new Double2D(1,1.2);
        startButton.initializeGeneralObject(Double2D.multiply(Settings.getInstance().centerPoint(),startButtonOffset));
        startButton.initializeImageObject("asteroidBig",7,0,8);
        startButton.initializeTextObject("start",32,8,true);

    }

    public void update(double delta){
        if(isActive() && startButton.getButtonPressed()) {
            if(startButton.getBlinker()==null){
                startButton.blink(700,7,false);
                title.blink(700,7,false);
            }else if(!startButton.getBlinker().isWorking()){
                terminate();
                setActive(false);
                ObjectManager.getInstance().startGame();
            }

        }
    }

    private void terminate(){
        title.setAlive(false);
        startButton.setAlive(false);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

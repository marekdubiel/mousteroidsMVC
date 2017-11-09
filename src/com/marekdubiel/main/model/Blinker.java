package com.marekdubiel.main.model;

import com.marekdubiel.main.view.Sprite;

import static java.lang.System.currentTimeMillis;

public class Blinker {
    private Sprite sprite;
    private long time;
    private Long timeOfCreation;
    private int blinks;
    private int currentBlink;
    private boolean working;
    private boolean visibleAfter;

    public Blinker(Sprite sprite, long time, int blinks, boolean visibleAfter){
        this.sprite = sprite;
        this.blinks = blinks;
        this.visibleAfter = visibleAfter;
        this.time = time;
        timeOfCreation = currentTimeMillis();
        currentBlink = 0;
        setWorking(true);
    }

    public void update(){
        if(isWorking()){
            if(blinks>currentBlink){
                blink();
            }
            else
                turnOff(visibleAfter);
        }
    }

    private void blink(){
        if((int)((currentTimeMillis()-timeOfCreation) / (time/blinks)) > currentBlink){
            sprite.setVisible(!sprite.isVisible());
            currentBlink++;
        }
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public void turnOff(boolean visibleAfter){
        sprite.setVisible(visibleAfter);
        setWorking(false);
    }
}

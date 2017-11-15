package com.marekdubiel.main.model;

import static java.lang.System.currentTimeMillis;

public class Swirler {
    int spins = 2;

    boolean working;
    private long time;
    private long timeOfCreation;
    private double direction;
    double speed;
    double startingRotation;
    PlayerObject parent;

    public Swirler(double direction, double speed, long time, double startingRotation, PlayerObject parent){
        this.parent = parent;
        this.time = time;
        this.direction = direction;
        this.speed = speed;
        this.startingRotation = startingRotation;
        timeOfCreation = currentTimeMillis();
        setWorking(true);
        parent.setShootingDisabled(true);

    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public void update(){
        parent.setDirection(direction);
        parent.setSpeed(speed);
        parent.setRotation(calculateRotation());
        if((currentTimeMillis()-timeOfCreation)>time){
            setWorking(false);
            parent.setShootingDisabled(false);
        }
    }

    private double calculateRotation(){
        return ((currentTimeMillis()-timeOfCreation)/(double)time)*360*spins;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }
}

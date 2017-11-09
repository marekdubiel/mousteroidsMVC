package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;

public class BulletObject extends CollidableObject {

    public BulletObject(Double2D position, double direction){
        super.initializeCollidableObject("bullet",2, position, direction,1);
        setSpeed(20);
        setMaxSpeed(getSpeed());
    }

    public void update(double delta){
        super.update(delta);
        killIfOutside();
    }

    private void killIfOutside(){
        if(getPosition().getX()>Settings.getInstance().getWindowWidth()*1.1 || getPosition().getX()<Settings.getInstance().getWindowWidth()*-0.1)
            setAlive(false);
        if(getPosition().getY()>Settings.getInstance().getWindowHeight()*1.1 || getPosition().getY()<Settings.getInstance().getWindowHeight()*-0.1)
            setAlive(false);
    }
}


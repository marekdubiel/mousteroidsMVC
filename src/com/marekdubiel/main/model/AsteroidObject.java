package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;

public class AsteroidObject extends CollidableObject {

    public AsteroidObject(Double2D position, double direction){
        super.initializeCollidableObject("asteroidSmall", 2, position,direction,1);
        setSpeed(1+(2*Settings.getInstance().getDifficulty()));
        setMaxSpeed(getSpeed());
    }

    @Override
    public void update(double delta){
        super.update(delta);
        wrapAround();
        rotate(delta);
    }

    private void wrapAround(){
        if(getPosition().getX()>Settings.getInstance().getWindowWidth()*1.1)
            setPosition(Double2D.add(getPosition(),new Double2D(-Settings.getInstance().getWindowWidth()*1.2,0)));
        if(getPosition().getX()<Settings.getInstance().getWindowWidth()*-0.1)
            setPosition(Double2D.add(getPosition(),new Double2D(Settings.getInstance().getWindowWidth()*1.2,0)));
        if(getPosition().getY()>Settings.getInstance().getWindowHeight()*1.1)
            setPosition(Double2D.add(getPosition(),new Double2D(0,-Settings.getInstance().getWindowHeight()*1.2)));
        if(getPosition().getY()<Settings.getInstance().getWindowHeight()*-0.1)
            setPosition(Double2D.add(getPosition(),new Double2D(0,Settings.getInstance().getWindowHeight()*1.2)));
    }

    private void rotate(double delta){
        setRotation(getRotation() + 5.0 * delta);
    }

}

package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Calculate;
import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.view.Particle;

import static java.lang.System.currentTimeMillis;

public class ParticleController {
    private boolean active;
    private Double2D position;
    private double rotation;
    private double scale;
    private double direction;
    private double speed;
    private Particle particle;
    private long disableTime;

    public ParticleController(Double2D position, double direction, double speed, double size, long lifespan){
        setPosition(position);
        setDirection(direction);
        setSpeed(speed);
        setRotation(0);
        setScale(size);
        setActive(true);
        particle = new Particle(size);
        disableTime = currentTimeMillis()+lifespan;


    }

    public void update(){
        setSpeed(speed*0.9);
        setPosition(Calculate.pointByDirectionAndDistance(position,direction,speed));
        setRotation(rotation*1.1);
        setScale(scale*0.9);
        particle.update(position,rotation,scale);
        if(currentTimeMillis()>disableTime)
            disable();
    }

    private void disable(){
        particle.setNeeded(false);
        setActive(false);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPosition(Double2D position) {
        this.position = position;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}

package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;

import java.util.ArrayList;
import java.util.List;

public class ParticleSystem {
    private List<ParticleController> controllers;

    public ParticleSystem(){
        initializeSystem();
    }

    private void initializeSystem(){
        controllers = new ArrayList<>();
    }

    public void update(){
        if(controllers!=null) {
            controllers.removeIf(controller -> !controller.isActive());
            controllers.forEach(controller -> controller.update());
        }
    }

    public boolean hasParticles(){
        if(controllers==null)
            return false;
        if(controllers.size()==0)
            return false;
        return true;
    }

    public void explode(Double2D position, double size){
        int particles = (int)(size*2);
        double directionOffset = Math.random()*360;
        for(int i=0;i<particles;i++) {
            double direction = i * (360 / particles) + directionOffset;
            double speed = size/5 + Math.random()*size;
            long lifeSpan = (long)(size*100 + Math.random()*size*100);
            controllers.add(new ParticleController(position, i * (360 / particles) + direction, speed, size, lifeSpan));
        }
    }

}

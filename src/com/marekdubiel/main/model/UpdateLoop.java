package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Updatable;

public class UpdateLoop implements Runnable {

    private int fps;
    private long lastUpdate;
    private Updatable instanceToUpdate;

    public UpdateLoop(int fps, Updatable instanceToUpdate){
        this.fps = fps;
        this.instanceToUpdate = instanceToUpdate;
        lastUpdate = System.currentTimeMillis();
    }

    public void run() {
        while (instanceToUpdate.getRuning()) {
            try {
                double delta = calculateDelta();
                lastUpdate = System.currentTimeMillis();
                update(delta);
                long waitingTime = Math.max(lastUpdate - System.currentTimeMillis() + 1000 / fps, 0);
                Thread.sleep(waitingTime);
            } catch (InterruptedException e) {
            }
        }
    }

    private double calculateDelta(){
        long timeSinceLastUpdate = System.currentTimeMillis() - lastUpdate;
        return timeSinceLastUpdate / (1000.0/fps);
    }

    private void update(double delta){
        instanceToUpdate.update(delta);
    }

}

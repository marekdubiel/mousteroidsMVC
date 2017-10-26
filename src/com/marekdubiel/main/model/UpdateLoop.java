package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Renderable;
import com.marekdubiel.main.additional.Updatable;

public class UpdateLoop implements Runnable {

    private int fps;
    private long lastUpdate;
    private Updatable instanceToUpdate;
    private Renderable instanceToRender;

    public UpdateLoop(int fps, Updatable instanceToUpdate, Renderable instanceToRender){
        this.fps = fps;
        this.instanceToUpdate = instanceToUpdate;
        this.instanceToRender = instanceToRender;
        lastUpdate = System.currentTimeMillis();
    }

    public synchronized void run() {
        while (instanceToUpdate.getRuning() && instanceToRender.getReady()) {
            try {
                double delta = calculateDelta();
                lastUpdate = System.currentTimeMillis();
                update(delta);
                render();
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

    private void render(){
        instanceToRender.render();
    }

}

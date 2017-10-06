package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Updatable;

import java.util.List;

public class ObjectManager implements Updatable {

    private static ObjectManager instance;
    private boolean running;

    private List<ObjectAsteroid> asteroids;
    private List<ObjectBullet> bullets;
    private ObjectPlayer player;

    public static ObjectManager getInstance() {
        if(instance==null)
            instance = new ObjectManager();
        return instance;
    }

    private ObjectManager(){

    }

    public void initializeModel() {
        setRunning(true);
        runMainLoop();
    }

    private void runMainLoop(){
        int fps = Settings.getInstance().getFps();
        Thread mainLoopThread = new Thread(new UpdateLoop(fps,ObjectManager.getInstance()));
        mainLoopThread.start();
    }

    @Override
    public void update(double delta){

    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public boolean getRuning(){
        return running;
    }

}

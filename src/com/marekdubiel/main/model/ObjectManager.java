package com.marekdubiel.main.model;

public class ObjectManager implements Updatable{

    private static ObjectManager instance;
    private boolean running;

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
        Thread mainLoopThread = new Thread(new MainUpdateLoopAction(fps,ObjectManager.getInstance()));
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

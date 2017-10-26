package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Updatable;
import com.marekdubiel.main.view.ViewManager;

public class ObjectManager implements Updatable {

    private static ObjectManager instance;
    private boolean running;
    private GameState gameState;

    public static ObjectManager getInstance() {
        if(instance==null)
            instance = new ObjectManager();
        return instance;
    }

    private ObjectManager(){
    }

    public void initializeModel() {
        startMenu();
        setRunning(true);
    }

    public void runMainLoop(){
        int fps = Settings.getInstance().getFps();
        Thread mainLoopThread = new Thread(new UpdateLoop(fps,ObjectManager.getInstance(),ViewManager.getInstance()));
        mainLoopThread.start();
    }

    public void startMenu(){
        Menu.getInstance().start();
        setGameState(gameState.MENU);
    }

    public void startGame(){
        Game.getInstance().start();
        setGameState(gameState.GAME);

    }

    @Override
    public void update(double delta){
        Game.getInstance().update(delta);
        Menu.getInstance().update(delta);
        //ViewManager.getInstance().render();
    }


    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public boolean getRuning(){
        return running;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }


}

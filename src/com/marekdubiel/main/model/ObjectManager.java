package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Updatable;
import com.marekdubiel.main.view.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;

public class ObjectManager implements Updatable {

    private static ObjectManager instance;
    private ArrayList<SimpleObject> objects;
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
        setRunning(true);
        runMainLoop();
        objects = new ArrayList<>();
    }

    public void runMainLoop(){
        int fps = Settings.getInstance().getFps();
        Thread mainLoopThread = new Thread(new UpdateLoop(fps,ObjectManager.getInstance(),ViewManager.getInstance()));
        mainLoopThread.start();
    }

    public void start(){
        initializeGUI();
        startMenu();
    }

    public void initializeGUI(){
        GUI.getInstance().initialize();
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
        GUI.getInstance().update();
        Game.getInstance().update(delta);
        Menu.getInstance().update(delta);
        updateObjects(delta);
    }


    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public boolean getRuning(){
        return running;
    }
    public void updateObjects(double delta){
        if(objects!=null)
            objects.removeIf(object -> object.getAlive()==false);
        objects.forEach(object -> object.update(delta));
    }

    public void addObject(SimpleObject object){
        object.setAlive(true);
        objects.add(object);
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }


}

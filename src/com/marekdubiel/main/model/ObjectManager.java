package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Updatable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ObjectManager implements Updatable {

    private static ObjectManager instance;
    private boolean running;
    private GameState gameState;

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
        startMenu();
    }

    private void runMainLoop(){
        int fps = Settings.getInstance().getFps();
        Thread mainLoopThread = new Thread(new UpdateLoop(fps,ObjectManager.getInstance()));
        mainLoopThread.start();
    }

    public void startMenu(){
        setGameState(gameState.MENU);
    }

    public void startGame(){
        setGameState(gameState.GAME);
        initializeObjectLists();
        initializePlayer();
    }

    private void initializeObjectLists(){
        asteroids =new ArrayList<>();
        bullets = new ArrayList<>();
    }

    private void initializePlayer(){
        player = new ObjectPlayer();
    }

    @Override
    public void update(double delta){

        player.update(delta);

        manageGameObjects(asteroids,delta);
        manageGameObjects(bullets,delta);
    }

    public void manageGameObjects(List<? extends GameObject> instances, double delta) {
        for(int i=0;i<instances.size();i++)
            instances.get(i).update(delta);
        instances.removeIf(GameObject -> GameObject.getAlive()==false);
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

    public void spawnAsteroid(){

    }

    public void spawnBullet(){

    }

}

package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Updatable;
import com.marekdubiel.main.view.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectManager implements Updatable {

    private static ObjectManager instance;
    private volatile List<SimpleObject> objects;
    private boolean running;
    private Menu menu;
    private Game game;

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
        objects = Collections.synchronizedList(new ArrayList());
        menu = new Menu();
        game = new Game();
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
        if(GUI.getInstance().getHeartsAmount()==0)
            menu.start("game over", "restart");
        else
            menu.start("mAuSTEROIDS", "start");


    }

    public void startGame(){
        cleanUpObjects();
        game.start();
        GUI.getInstance().resetScore();
    }

    @Override
    public void update(double delta){
        GUI.getInstance().update();
        updateGameAndMenu();
        updateObjects(delta);
    }

    public void updateGameAndMenu(){
        if(game!=null)
            game.update();
        if(menu!=null)
            menu.update();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public boolean getRuning(){
        return running;
    }
    public void updateObjects(double delta){
        if(objects!=null) {
            objects.removeIf(object -> !object.isAlive());
            objects.forEach(object -> object.update(delta));
        }
    }

    public void addObject(SimpleObject object){
        object.setAlive(true);
        objects.add(object);
    }

    private void cleanUpObjects(){
        if(objects!=null) {
            for(SimpleObject object : objects){
                if(object instanceof AsteroidObject || object instanceof BulletObject){
                    object.setAlive(false);
                }
            }
        }
    }

    public List<SimpleObject> getObjects() {
        return objects;
    }

    public Game getGame() {
        return game;
    }



}

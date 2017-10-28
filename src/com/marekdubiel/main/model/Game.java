package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game instance;
    private AsteroidSpawner spawner = new AsteroidSpawner();
    private List<AsteroidObject> asteroids;
    private List<BulletObject> bullets;
    private PlayerObject player;
    private GUI gui;
    private GameOverSubmenu gameOverSubmenu;

    public static Game getInstance() {
        if(instance==null)
            instance = new Game();
        return instance;
    }

    private Game(){

    }

    public void start() {
        initializeObjectLists();
        initializePlayer();
        initializeGUI();
    }

    public void update(double delta){
        //player.update(delta);
        //gui.update(delta);
        manageGameObjects(asteroids,delta);
        manageGameObjects(bullets,delta);
    }

    public void terminate(){
        asteroids.clear();
        bullets.clear();
        player = null;
        gui = null;
    }

    private void initializeObjectLists(){
        asteroids =new ArrayList<>();
        bullets = new ArrayList<>();
    }

    private void initializePlayer(){
        player = new PlayerObject();
    }

    private void initializeGUI(){
        gui = new GUI();
    }

    public void manageGameObjects(List<? extends GameObject> instances, double delta) {
        if(instances!=null) {
            instances.forEach(instance -> instance.update(delta));
            instances.removeIf(instance -> instance.getAlive() == false);
        }
    }

    public void addAsteroid(AsteroidObject asteroid){
        asteroids.add(asteroid);
    }

    public void addBullet(BulletObject bullet){
        bullets.add(bullet);
    }

    public Double2D getPlayerPosition(){
        return player.getPosition();
    }
}

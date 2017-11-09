package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private AsteroidSpawner asteroidSpawner;
    private BulletSpawner bulletSpawner;
    private PlayerObject player;

    public Game(){

    }

    public void start() {
        initializePlayer();
        initializeBulletSpawner();
        initializeAsteroidSpawner();

    }

    private void initializePlayer(){
        player = new PlayerObject();
    }

    private void initializeBulletSpawner(){
        bulletSpawner = new BulletSpawner(player);
    }

    private void initializeAsteroidSpawner(){
        asteroidSpawner = new AsteroidSpawner(player);
    }

    public void update(double delta){
        if(bulletSpawner!=null)
            bulletSpawner.shootBulletIfPrepared();
    }

    public BulletSpawner getBulletSpawner() {
        return bulletSpawner;
    }

    public AsteroidSpawner getAsteroidSpawner() {
        return asteroidSpawner;
    }
}

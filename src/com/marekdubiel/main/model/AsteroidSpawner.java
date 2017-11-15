package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Calculate;
import com.marekdubiel.main.additional.Double2D;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;

public class AsteroidSpawner {

    SimpleObject target;
    private Queue<PendingAsteroid> pendingAsteroids;
    private int asteroidCount;
    private boolean spawning;

    public AsteroidSpawner(SimpleObject target){
        this.target = target;
        initializeTimer();
        asteroidCount = 0;

    }

    private void initializeTimer(){
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000),ae -> trySpawningAsteroid()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        setSpawning(true);
    }

    private void trySpawningAsteroid(){
        if(asteroidCount<25 && spawning) {
            int sideToSpawn = (int)(Math.random()*4);
            int asteroidSize = Math.max((int)(Math.random()*3)*2,1);
            spawnAsteroid(sideToSpawn,asteroidSize);
            setAsteroidCount(getAsteroidCount()+asteroidSize);
        }
    }

    private void spawnAsteroid(int side, int size) {
        double startingX = 0;
        double startingY = 0;

        switch (side){
            case 0:
                startingX = Settings.getInstance().getWindowWidth() * 1.1;
                startingY = Settings.getInstance().getWindowHeight()*Math.random()*0.8 + Settings.getInstance().getWindowHeight()*0.1;
                break;
            case 1:
                startingX = Settings.getInstance().getWindowWidth()*Math.random()*0.8 + Settings.getInstance().getWindowWidth()*0.1;
                startingY = Settings.getInstance().getWindowHeight() * 1.1;
                break;
            case 2:
                startingX = Settings.getInstance().getWindowWidth() * -0.1;
                startingY = Settings.getInstance().getWindowHeight()*Math.random()*0.8 + Settings.getInstance().getWindowHeight()*0.1;
                break;
            case 3:
                startingX = Settings.getInstance().getWindowWidth()*Math.random()*0.8 + Settings.getInstance().getWindowWidth()*0.1;
                startingY = Settings.getInstance().getWindowHeight() * -0.1;
                break;
        }
        Double2D asteroidPosition = new Double2D(startingX,startingY);

        new AsteroidObject(size, asteroidPosition, Calculate.direction(target.getPosition(), asteroidPosition),this);
    }

    public void setSpawning(boolean spawning){
        this.spawning = spawning;
    }

    public void spawnAsteroidsIfPending(){
        if (pendingAsteroids!=null){
            while(!pendingAsteroids.isEmpty()){
                PendingAsteroid pendingAsteroid = pendingAsteroids.poll();
                new AsteroidObject(pendingAsteroid.getSize(), pendingAsteroid.getPosition(),pendingAsteroid.getDirection(), this);
            }
        }
    }

    public void splitAsteroid(int size, Double2D position, double direction){
        if(pendingAsteroids==null)
            pendingAsteroids = new LinkedList<>();
        pendingAsteroids.add(new PendingAsteroid(size/2, Calculate.pointByDirectionAndDistance(position, direction+90, 10+size/2), direction+90));
        pendingAsteroids.add(new PendingAsteroid(size/2, Calculate.pointByDirectionAndDistance(position, direction-90, 10+size/2), direction-90));

    }

    public int getAsteroidCount() {
        return asteroidCount;
    }

    public void setAsteroidCount(int asteroidCount) {
        this.asteroidCount = asteroidCount;
    }
}

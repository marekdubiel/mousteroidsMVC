package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Calculate;
import com.marekdubiel.main.additional.Double2D;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class AsteroidSpawner {

    private int asteroidCount;
    private boolean spawning;

    public AsteroidSpawner(){
        initializeTimer();
        asteroidCount = 0;
        spawning = false;

    }

    private void initializeTimer(){
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000),ae -> trySpawningAsteroid()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void trySpawningAsteroid(){
        if(asteroidCount<25 && spawning) {
            int sideToSpawn= (int)(Math.random()*4);
            spawnAsteroid(sideToSpawn);
        }
    }

    private void spawnAsteroid(int side) {
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


        AsteroidObject asteroid = new AsteroidObject();
        asteroid.setPosition(new Double2D(startingX,startingY));
        asteroid.setRotation(Calculate.direction(asteroid.getPosition(),Game.getInstance().getPlayerPosition()));
        Game.getInstance().addAsteroid(asteroid);
    }

    public void setSpawning(boolean spawning){
        this.spawning = spawning;
    }
}

package com.marekdubiel.main.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class AsteroidSpawner {

    public AsteroidSpawner(){
        initializeTimer();
    }

    private void initializeTimer(){
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000),ae -> spawnAsteroid()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void spawnAsteroid(){

    }
}

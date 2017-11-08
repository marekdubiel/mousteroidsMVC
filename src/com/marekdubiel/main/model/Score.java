package com.marekdubiel.main.model;

import com.marekdubiel.main.additional.Double2D;

public class Score {
    int score;
    int previousScore;
    Double2D position;
    SimpleObject textObject;

    public Score(Double2D position){
        this.position = position;
        setScore(0);
        setPreviousScore(0);
        createTextObject(position,score);
    }

    private void createTextObject(Double2D position, int score){
        textObject= new SimpleObject();
        textObject.initializeGeneralObject(position);
        textObject.initializeTextObject(Integer.toString(score),38,8,true);
    }

    public void setScore(int score){
        this.score = score;
    }

    private void setPreviousScore(int previousScore){
        this.previousScore = previousScore;
    }

    public void update(){
        if(score != previousScore){
            textObject.setAlive(false);
            createTextObject(position,score);
            previousScore = score;
        }
    }
}

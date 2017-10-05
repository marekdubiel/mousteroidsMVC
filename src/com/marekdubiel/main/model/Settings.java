package com.marekdubiel.main.model;

public class Settings {
    private static Settings instance = null;

    private int fps;
    private int windowWidth;
    private int windowHeight;
    private double difficulty;
    private int lifeAmount;

    public static Settings getInstance(){
        if(instance==null)
            instance = new Settings();
        return instance;
    }

    private Settings(){

    }

    public void setWindowWidth(int windowWidth){
        this.windowWidth = windowWidth;
    }

    public void setWindowHeight(int windowHeight){
        this.windowHeight = windowHeight;
    }

    public void setDifficulty(double difficulty){
        this.difficulty = difficulty;
    }

    public void setLifeAmount(int lifeAmount){
        this.lifeAmount = lifeAmount;
    }

    public int getWindowWidth(){
        return windowWidth;
    }

    public int getWindowHeight(){
        return windowHeight;
    }

    public double getDifficulty(){
        return difficulty;
    }

    public int getLifeAmount(){
        return lifeAmount;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getFps(){
        return fps;
    }
}

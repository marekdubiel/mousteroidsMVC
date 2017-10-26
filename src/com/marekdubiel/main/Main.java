package com.marekdubiel.main;

import javafx.application.Application;
import javafx.stage.Stage;

import com.marekdubiel.main.model.*;
import com.marekdubiel.main.view.*;
import com.marekdubiel.main.controller.*;


public class Main extends Application {

    public static void main(String[]args){
        launch(args);
    }

    @Override
    public void start (Stage stage) throws Exception {
        setRules();
        initializeView(stage);
        initializeController();
        initializeModel();
    }

    @Override
    public void stop (){
        ObjectManager.getInstance().setRunning(false);
    }

    private void setRules(){
        Settings.getInstance().setFps(30);
        Settings.getInstance().setWindowWidth(640);
        Settings.getInstance().setWindowHeight(480);
        Settings.getInstance().setDifficulty(1.5);
        Settings.getInstance().setLifeAmount(5);
    }

    private void initializeModel(){
        ObjectManager.getInstance().initializeModel();
    }

    private void initializeView(Stage stage){
        ViewManager.getInstance().initializeView(stage);
    }

    private void initializeController(){
        InputManager.getInstance().initializeController();
    }

}

package com.marekdubiel.main.view;

import javafx.stage.Stage;

public class ViewManager {

    private static ViewManager instance = null;

    public static ViewManager getInstance(){
            if(instance==null)
                instance = new ViewManager();
            return instance;
    }

    private ViewManager(){

    }

    public void initializeView(Stage stage){
        Window window = new Window(stage);
    }

    public void render(double delta){

    }
}

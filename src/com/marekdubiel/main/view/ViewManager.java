package com.marekdubiel.main.view;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static ViewManager instance = null;
    private Window window;
    private List<Sprite> sprites;

    public static ViewManager getInstance(){
            if(instance==null)
                instance = new ViewManager();
            return instance;
    }

    private ViewManager(){

    }

    public void initializeView(Stage stage){
        window = new Window(stage);
        sprites = new ArrayList<>();
    }

    public void render(){
        //sprites.forEach(Sprite::render);
        //draw on canvas
        window.clear();
    }
}

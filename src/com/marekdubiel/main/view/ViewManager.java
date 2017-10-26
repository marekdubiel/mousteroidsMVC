package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Renderable;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManager implements Renderable {

    private static ViewManager instance = null;
    private Window window;
    private List<Sprite> sprites;
    private boolean ready;

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
        Platform.runLater(()-> {
            window.clear();
            window.drawPolygon(Color.RED,Color.BLUE);
            window.endFrame();
        });
    }

    public boolean getReady(){
        return ready;
    }

    public void setReady(boolean ready){
        this.ready = ready;
    }
}

package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Renderable;
import javafx.application.Platform;
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
        Platform.runLater(()-> {
            window.clear();
            drawSprites();
            window.endFrame();
        });
    }

    private void drawSprites(){
        if(sprites!=null)
            sprites.removeIf(sprite -> sprite.getNeeded()==false);
            sprites.forEach(sprite -> sprite.render(window.getGraphicsContext()));

    }

    public void addSprite(Sprite sprite){
        sprite.setNeeded(true);
        sprites.add(sprite);
    }

    public boolean getReady(){
        return ready;
    }

    public void setReady(boolean ready){
        this.ready = ready;
    }
}

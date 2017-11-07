package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Renderable;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManager implements Renderable {

    private static ViewManager instance = null;
    private Window window;
    private ShapeCreator shapeCreator;
    private List<Layer> layers;
    private boolean ready;

    public static ViewManager getInstance(){
            if(instance==null)
                instance = new ViewManager();
            return instance;
    }

    private ViewManager() {
    }
    public void initializeView(Stage stage){
        window = new Window(stage);
        shapeCreator = new ShapeCreator();
        initializeLayers();
    }

    private void initializeLayers(){
        layers = new ArrayList<>();
        for(int i=0;i<10;i++)
            layers.add(new Layer());
    }

    public void render(){
        Platform.runLater(()-> {
            window.clear();
            drawLayers();
            window.endFrame();
        });
    }

    private void drawLayers(){
        layers.forEach(layer -> layer.drawSprites(window.getGraphicsContext()));
    }

    public void addSprite(Sprite sprite, int layer){
        layers.get(sprite.getLayer()).addSprite(sprite);
    }

    public boolean getReady(){
        return ready;
    }

    public void setReady(boolean ready){
        this.ready = ready;
    }

    public ShapeCreator getShapeCreator() {
        return shapeCreator;
    }

}

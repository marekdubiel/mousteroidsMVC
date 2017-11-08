package com.marekdubiel.main.controller;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.model.Settings;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;



public class InputManager {

    private static InputManager instance;
    private EventHandler<MouseEvent> mouseButtonDown;
    private EventHandler<MouseEvent> mouseButtonUp;
    private boolean action;
    private Double2D focusPoint;

    public static InputManager getInstance() {
        if(instance==null)
            instance = new InputManager();
        return instance;
    }

    private InputManager(){

    }

    public void initializeController(Scene scene){
        Double2D initialOffset = new Double2D(1,0.3);
        setFocusPoint(Double2D.multiply(Settings.getInstance().centerPoint(),initialOffset));
        setAction(false);
        createHandlers();
        linkHandlers(scene);

    }

    private void createHandlers(){

        mouseButtonDown = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent){
                passMousePosition(mouseEvent);
                setAction(true);
            }
        };

        mouseButtonUp = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent){
                passMousePosition(mouseEvent);
                setAction(false);
            }
        };
    }

    private void passMousePosition(MouseEvent mouseEvent){
        setFocusPoint(new Double2D(mouseEvent.getSceneX(),mouseEvent.getSceneY()));
    }

    private void linkHandlers(Scene scene){
        scene.setOnMouseMoved(mouseButtonUp);
        scene.setOnMouseReleased(mouseButtonUp);

        scene.setOnMousePressed(mouseButtonDown);
        scene.setOnMouseDragged(mouseButtonDown);
    }

    private void setFocusPoint(Double2D focusPoint){
        this.focusPoint = focusPoint;
    }

    public Double2D getFocusPoint() {
        return focusPoint;
    }

    private void setAction(boolean action){
        this.action= action;
    }

    public boolean getAction(){
        return action;
    }
}

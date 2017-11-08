package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.model.ObjectManager;
import com.marekdubiel.main.model.Settings;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

public class Window {
    private Pane root;
    private Canvas canvas;
    private Scene scene;
    private Stage stage;
    private GraphicsContext graphicsContext;

    public Window(Stage stage){
        this.stage = stage;
        initializeWindow();
    }

    private void initializeWindow(){
        stage.setTitle("mAuSTEROIDS");
        root = new Pane();
        canvas = new Canvas(Settings.getInstance().getWindowWidth(), Settings.getInstance().getWindowHeight());
        graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        root.setCursor(Cursor.NONE);
        graphicsContext.setLineJoin(StrokeLineJoin.ROUND);
        clear();
        endFrame();
        ViewManager.getInstance().setReady(true);
        exitProgramIfWindowClosed();
    }

    public void clear(){
        graphicsContext.setFill(Color. BLACK);
        graphicsContext.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void endFrame(){
        stage.show();
    }

    public GraphicsContext getGraphicsContext(){
        return graphicsContext;
    }

    private void exitProgramIfWindowClosed(){
        stage.setOnCloseRequest(event -> {
            ObjectManager.getInstance().setRunning(false);
            Platform.exit();
        });

    }
}

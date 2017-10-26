package com.marekdubiel.main.view;

import com.marekdubiel.main.model.Settings;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window {
    private Pane root;
    private Canvas canvas;
    private Stage stage;
    private GraphicsContext graphicsContext;

    public Window(Stage stage){
        this.stage = stage;
        initializeWindow();
    }

    private void initializeWindow(){
        stage.setTitle("mousteroids");
        Group root = new Group();
        canvas = new Canvas(Settings.getInstance().getWindowWidth(), Settings.getInstance().getWindowWidth());
        graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void clear(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        stage.show();
    }

    public void drawPolygon(Color strokeColor, Color fillColor){

        graphicsContext.setStroke(strokeColor);
        graphicsContext.setFill(fillColor);
        //polygons position rotation stroke weight stroke colour fill colour
        stage.show();
    }

    public void drawText(String text, double size, Color textColor){

    }
}

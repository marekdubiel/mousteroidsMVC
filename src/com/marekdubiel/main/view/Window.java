package com.marekdubiel.main.view;

import com.marekdubiel.main.model.Settings;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window {
    private Pane root;
    private Stage stage;

    public Window(Stage stage){
        stage.setTitle("mousteroids");
        Group root = new Group;
        Canvas canvas = new Canvas(Settings.getInstance().getWindowWidth(), Settings.getInstance().getWindowWidth());
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void draw(){
        //graphicContext.
        //polygons position rotation stroke weight stroke colour fill colour
        //text
    }
}

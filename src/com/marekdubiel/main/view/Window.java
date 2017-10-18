package com.marekdubiel.main.view;

import com.marekdubiel.main.model.Settings;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window {
    private Pane root;
    private Stage stage;

    public Window(Stage stage){
        stage.setScene(new Scene(createContent()));
        stage.setResizable(false);
        stage.setTitle("mousteroids");
        stage.show();
    }

    public Scene getScene(){
        return new Scene(createContent());
    }

    private Parent createContent(){
        root = new Pane();
        root.setPrefSize(Settings.getInstance().getWindowWidth(), Settings.getInstance().getWindowWidth());
        root.setStyle("-fx-background-color: #000000");
        return root;
    }
}

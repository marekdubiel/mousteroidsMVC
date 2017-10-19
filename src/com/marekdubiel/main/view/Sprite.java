package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.model.GameObject;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

import static com.marekdubiel.main.view.ShapeOperations.generateRandomAsteroidShape;
import static com.marekdubiel.main.view.ShapeOperations.loadFromFile;

public class Sprite {
    Node view;
    GameObject parentObject;
    List<Double2D> vertices = new ArrayList<>();
    Double2D position;
    double rotation;
    double scale;
    Color fill;

    public Sprite(GameObject object, Shape shape){
        this.parentObject = object;
        loadShape(shape);
    }

    private void loadShape(Shape shape){
        switch(shape){
            case SPACESHIP:
                createView(loadFromFile("resources/shapes.csv",0),Color.BLACK);
                fill = Color.BLACK;
                break;
            case JET:
                createView(loadFromFile("resources/shapes.csv",2),Color.BLACK);
                fill = Color.BLACK;
                break;
            case CROSSHAIR:
                createView(loadFromFile("resources/shapes.csv",4),Color.BLACK);
                fill = Color.BLACK;
                break;
            case BULLET:
                createView(loadFromFile("resources/shapes.csv",6),Color.BLACK);
                fill = Color.BLACK;
                break;
            case HEART_EMPTY:
                createView(loadFromFile("resources/shapes.csv",6),Color.BLACK);
                fill = Color.BLACK;
                break;
            case HEART_FULL:
                createView(loadFromFile("resources/shapes.csv",6),Color.WHITE);
                fill = Color.WHITE;
                break;
            case ASTEROID_SML:
                createView(generateRandomAsteroidShape(1),Color.BLACK);
                fill = Color.BLACK;
                break;
            case ASTEROID_MED:
                createView(generateRandomAsteroidShape(2),Color.BLACK);
                fill = Color.BLACK;
                break;
            case ASTEROID_BIG:
                createView(generateRandomAsteroidShape(3),Color.BLACK);
                break;
        }
    }

    public void createView(List<Double2D> polygonVertices, Color color){
        Node view;
        Polygon asteroidShape = new Polygon();

        setView(view);
        setBounds(polygonVertices);

    }

    public void setView(Node view){
        this.view = view;
    }

    public Node getView(){
        return view;
    }

    public void setBounds(List<Double2D> vertices){
        this.vertices = vertices;
    }

    public List<Double2D> getBounds(){
        return vertices;
    }

    public void render(){

    }
}

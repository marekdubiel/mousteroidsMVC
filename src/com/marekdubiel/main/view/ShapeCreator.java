package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;
import javafx.scene.paint.Color;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ShapeCreator {

    public static void create(ImageSprite parent, String shape){
        String path = "resources/shapes_table.csv";
        switch(shape){
            case "spaceship":
                parent.setVertices(loadFromFile(path,0));
                parent.setFillColor(Color.BLACK);
                break;
            case "jet":
                parent.setVertices(loadFromFile(path,2));
                parent.setFillColor(Color.BLACK);
                break;
            case "crosshair":
                parent.setVertices(loadFromFile(path,4));
                parent.setFillColor(Color.BLACK);
                break;
            case "bullet":
                parent.setVertices(loadFromFile(path,6));
                parent.setFillColor(Color.BLACK);
                break;
            case "heartEmpty":
                parent.setVertices(loadFromFile(path,8));
                parent.setFillColor(Color.BLACK);
                break;
            case "heartFull":
                parent.setVertices(loadFromFile(path,8));
                parent.setFillColor(Color.WHITE);
                break;
            case "asteroidSmall":
                parent.setVertices(generateRandomAsteroidShape(1));
                parent.setFillColor(Color.BLACK);
                break;
            case "asteroidMedium":
                parent.setVertices(generateRandomAsteroidShape(2));
                parent.setFillColor(Color.BLACK);
                break;
            case "asteroidBig":
                parent.setVertices(generateRandomAsteroidShape(3));
                parent.setFillColor(Color.BLACK);
                break;
            default:
                break;
        }
        parent.setStrokeColor(Color.WHITE);
        parent.setStrokeWidth(1.0);

    }

    private static ArrayList<Double2D> loadFromFile(String filePath, int shapeRow){
        File file = new File(ShapeCreator.class.getClassLoader().getResource(filePath).toExternalForm());
        ArrayList <Double2D> verticesFromFile = new ArrayList<>();

        try{
            Scanner fileInput = new Scanner(file);
            int currentRow = 0;
            while(currentRow<shapeRow) {
                fileInput.nextLine();
                currentRow++;
            }
            String[] xLine = fileInput.nextLine().split(",");
            String[] yLine = fileInput.nextLine().split(",");
            for(int i=1;i<xLine.length;i++){
                verticesFromFile.add(new Double2D(Double.parseDouble(xLine[i]),Double.parseDouble(yLine[i])));
            }

        }catch(FileNotFoundException | NoSuchElementException exception){
            exception.printStackTrace();
        }

        return verticesFromFile;
    }

    public static ArrayList<Double2D> generateRandomAsteroidShape(int size){
        int sides = 5 +(int)(Math.random()*4);
        ArrayList<Double2D> vertices = new ArrayList<Double2D>();
        for(int i=0;i<sides; i++){
            double angle = i*(360.0/sides) + Math.random()*(360.0/sides);
            double radius = Math.random()*7.5 + 5;
            vertices.add(new Double2D(radius*Math.cos(Math.toRadians(angle)),radius*Math.sin(Math.toRadians(angle))));
        }
        return vertices;
    }
}

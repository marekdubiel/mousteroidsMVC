package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ShapeOperations {

    public static List<Double2D> loadFromFile(String filePath, int shapeRow){
        File file = new File(filePath);
        List <Double2D> verticesFromFile = new ArrayList<>();

        try{
            Scanner fileInput = new Scanner(file);
            int currentRow = 0;
            while(currentRow<shapeRow){
                fileInput.nextLine();
            }
            String[] xLine = fileInput.nextLine().split(";");
            String[] yLine = fileInput.nextLine().split(";");
            for(int i=1;i<xLine.length;i++){
                verticesFromFile.add(new Double2D(Double.parseDouble(xLine[i]),Double.parseDouble(yLine[i])));
            }

        }catch(FileNotFoundException |NoSuchElementException exception){
            exception.printStackTrace();
        }

        return verticesFromFile;
    }

    public static List<Double2D> generateRandomAsteroidShape(int size){
        int sides = 5 +(int)(Math.random()*4);
        List<Double2D> vertices = new ArrayList<Double2D>();
        for(int i=0;i<sides; i++){
            double angle = i*(360.0/sides) + Math.random()*(360.0/sides);
            double radius = Math.random()*7.5 + 5;
            vertices.add(new Double2D(radius*Math.cos(Math.toRadians(angle)),radius*Math.sin(Math.toRadians(angle))));
        }
        return vertices;
    }
}

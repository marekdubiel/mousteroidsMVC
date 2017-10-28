package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.model.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static com.marekdubiel.main.additional.Geometry.rotateVertices;
import static com.marekdubiel.main.additional.Geometry.scaleVertices;
import static com.marekdubiel.main.additional.Geometry.translateVertices;

public class ImageSprite extends Sprite {
    private ArrayList<Double2D> vertices;
    private ArrayList<Double2D> updatedVertices;
    private Color fillColor;
    private Color strokeColor;
    private double strokeWidth;

    public ImageSprite(String shape, GameObject parent){
        super(parent);
        asignShape(shape);
        activateSprite();
    }

    private void activateSprite(){
        ViewManager.getInstance().addSprite(this);
        super.update();
    }

    private void asignShape(String shape){
        ShapeCreator.create(this,shape);
    }

    public void setVertices(ArrayList<Double2D> vertices){
        this.vertices = vertices;
    }

    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    public void setStrokeColor(Color strokeColor){
        this.strokeColor = strokeColor;
    }

    public void setStrokeWidth(double strokeWidth){
        this.strokeWidth = strokeWidth;
    }

    @Override
    public void update() {
        super.update();
        updatedVertices = scaleVertices(vertices,scale);
        updatedVertices = rotateVertices(updatedVertices,rotation);
        updatedVertices = translateVertices(updatedVertices,position);
    }

    @Override
    public void render(GraphicsContext graphicsContext){
        update();
        graphicsContext.setFill(fillColor);
        graphicsContext.setStroke(strokeColor);
        graphicsContext.setLineWidth(strokeWidth);
        if(fillColor!=null)
            graphicsContext.fillPolygon(calculateX(updatedVertices),calculateY(updatedVertices),calculatePoints(updatedVertices));
        graphicsContext.strokePolygon(calculateX(updatedVertices),calculateY(updatedVertices),calculatePoints(updatedVertices));
    }

    private double[] calculateX(ArrayList<Double2D> vertices){
        double[] xPoints = new double[vertices.size()];
        for(int i=0;i<vertices.size();i++)
            xPoints[i] = vertices.get(i).getX();
        return xPoints;
    }

    private double[] calculateY(ArrayList<Double2D> vertices){
        double[] yPoints = new double[vertices.size()];
        for(int i=0;i<vertices.size();i++)
            yPoints[i] = vertices.get(i).getY();
        return yPoints;
    }

    private int calculatePoints(ArrayList<Double2D> vertices){
        return vertices.size();
    }

    public List<Double2D> getBounds(){
        return vertices;
    }

}

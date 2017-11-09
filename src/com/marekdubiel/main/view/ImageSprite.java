package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.model.CollidableObject;
import com.marekdubiel.main.model.SimpleObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.File;
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

    public ImageSprite(String shape, int layer, SimpleObject parent){
        super(parent, layer);
        assignShape(shape);
        activateSprite(layer);
    }

    private void activateSprite(int layer){
        ViewManager.getInstance().addSprite(this, layer);
        super.update();
    }

    private void assignShape(String shape){
        ViewManager.getInstance().getShapeCreator().create(this,shape);
    }

    public void setVertices(ArrayList<Double2D> vertices){
        this.vertices = vertices;
    }

    public void setFillColor(Color fillColor){
        this.fillColor = fillColor;
    }

    public void reverseFillColor(){
        this.fillColor = Color.rgb(255-(int)(fillColor.getRed()*255),255-(int)(fillColor.getGreen()*255),255-((int)fillColor.getBlue()*255));
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
        updatedVertices = scaleVertices(vertices,getScale());
        updatedVertices = rotateVertices(updatedVertices,getRotation());
        updatedVertices = translateVertices(updatedVertices,getPosition());
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        update();
        if (isVisible()) {
            graphicsContext.setFill(fillColor);
            graphicsContext.setStroke(strokeColor);
            graphicsContext.setLineWidth(strokeWidth);
            if (fillColor != null)
                graphicsContext.fillPolygon(calculateX(updatedVertices), calculateY(updatedVertices), calculatePoints(updatedVertices));
            graphicsContext.strokePolygon(calculateX(updatedVertices), calculateY(updatedVertices), calculatePoints(updatedVertices));
        }
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

    public ArrayList<Double2D> getBounds(){
        return vertices;
    }

}

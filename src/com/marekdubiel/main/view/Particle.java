package com.marekdubiel.main.view;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.additional.Geometry;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Particle implements Drawable{
        private boolean needed;
        private ArrayList<Double2D> vertices;
        private ArrayList<Double2D> updatedVertices;

    public Particle(double size){
        setVertices(generateRandomParticleShape(size));
        ViewManager.getInstance().addDrawable(this,1);
    }

    public  static ArrayList<Double2D> generateRandomParticleShape(double size) {
        ArrayList<Double2D> vertices = new ArrayList<>();
        int sides = (int)(Math.random()*3)+3;
        for (int i = 0; i < sides; i++)
            vertices.add(new Double2D(Math.random() * size, Math.random() * size));
        return vertices;
    }

    public void update(Double2D position, double rotation, double scale){
        updatedVertices = Geometry.scaleVertices(vertices,scale);
        updatedVertices = Geometry.rotateVertices(updatedVertices,rotation);
        updatedVertices = Geometry.translateVertices(updatedVertices,position);
    }

    @Override
    public void render(GraphicsContext graphicsContext){
        if(updatedVertices!=null) {
            double[] xPoints = new double[updatedVertices.size()];
            for (int i = 0; i < xPoints.length; i++)
                xPoints[i] = updatedVertices.get(i).getX();
            double[] yPoints = new double[updatedVertices.size()];
            for (int i = 0; i < yPoints.length; i++)
                yPoints[i] = updatedVertices.get(i).getY();
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillPolygon(xPoints, yPoints, updatedVertices.size());
        }
    }

    @Override
    public boolean isNeeded() {
        return needed;
    }

    @Override
    public void setNeeded(boolean needed) {
        this.needed = needed;
    }

    public void setVertices(ArrayList<Double2D> vertices) {
        this.vertices = vertices;
    }
}

package com.marekdubiel.main.additional;

import java.util.Objects;

public class Double2D implements Comparable<Double2D>{
    private double x;
    private double y;

    public Double2D(double x, double y){
        setX(x);
        setY(y);
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    @Override
    public boolean equals(Object other){

        if (other==null)
            return false;
        if (other==this)
            return true;
        if (!(other instanceof Double2D))
            return false;
        Double2D otherDouble2D = (Double2D)other;
        return Objects.equals(this.getX(),otherDouble2D.getX()) && Objects.equals(this.getY(),otherDouble2D.getY());
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.getX(),this.getY());
    }

    public int compareTo(Double2D other){
        if (this.getY()<other.getY())
            return -1;
        if (this.getY()>other.getY())
            return 1;
        if (this.getX()<other.getX())
            return -1;
        if (this.getX()>other.getX())
            return 1;
        return 0;
    }

    public static Double2D add(Double2D first, Double2D second){
        if (first == null && second == null)
            return null;
        else if (first == null)
            return second;
        else if (second == null)
            return first;
        else {
            double x = first.getX() + second.getX();
            double y = first.getY() + second.getY();
            return new Double2D(x, y);
        }
    }

    public static Double2D subtract(Double2D first, Double2D second){
        if (first == null && second == null)
            return null;
        else if (first == null)
            return second;
        else if (second == null)
            return first;
        else {
            double x = first.getX() - second.getX();
            double y = first.getY() - second.getY();
            return new Double2D(x, y);
        }
    }

    public static Double2D multiply(Double2D first, Double2D second){
        if (first == null && second == null)
            return null;
        else if (first == null)
            return second;
        else if (second == null)
            return first;
        else {
            double x = first.getX() * second.getX();
            double y = first.getY() * second.getY();
            return new Double2D(x, y);
        }
    }
}

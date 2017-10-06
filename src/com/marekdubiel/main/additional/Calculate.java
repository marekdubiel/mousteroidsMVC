package com.marekdubiel.main.additional;

public class Calculate {

    public static double distance(Double2D from, Double2D to){
        double distance;
        distance = Math.sqrt(((to.getX()-from.getX())*(to.getX()-from.getX()))
                            +((to.getY()-from.getY())*(to.getY()-from.getY())));
        return distance;
    }

    public static double direction(Double2D from, Double2D to){
        double direction;
        direction = Math.toDegrees(Math.atan2(to.getY()-from.getY(), to.getX()-from.getX()));
        return direction;
    }
}

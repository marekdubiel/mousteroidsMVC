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
        if (direction<0){
            direction+=360;
        }
        return direction;
    }

    public static Double2D pointByDistanceAndDirection(Double2D fromPoint, double direction, double distance){
        double x = -distance * Math.cos(Math.toRadians(direction));
        double y = -distance * Math.sin(Math.toRadians(direction));
        return Double2D.add(fromPoint,new Double2D(x,y));
    }

    public static Double2D aproximateDouble2Ds(Double2D value, int decimalPoints) {
        double aproximatedX;
        double aproximatedY;
        int multiplier = 1;

        for(int i=1;i<=decimalPoints;i++)
            multiplier*=10;

        aproximatedX = ((Long)Math.round(value.getX() * multiplier)).doubleValue() / multiplier;
        aproximatedY = ((Long)Math.round(value.getY() * multiplier)).doubleValue() / multiplier;

        return new Double2D(aproximatedX,aproximatedY);
    }
}

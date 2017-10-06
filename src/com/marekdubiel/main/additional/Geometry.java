package com.marekdubiel.main.additional;

import java.util.ArrayList;
import java.util.Collections;

public class Geometry {
    public static boolean checkCollision(Collidable collidable1, Collidable collidable2){

        checkIfCollidingItself(collidable1,collidable2);

        ArrayList<Double2D> vertices1 = collidable1.getBoundingVertices();
        ArrayList<Double2D> vertices2 = collidable2.getBoundingVertices();

        if (!bothAreLegalPolygons(vertices1,vertices2))
            return false;

        vertices1 = rotateVertices(vertices1, collidable1.getRotation());
        vertices2 = rotateVertices(vertices2, collidable2.getRotation());

        vertices1 = translateVertices(vertices1, collidable1.getPosition());
        vertices2 = translateVertices(vertices2, collidable2.getPosition());

        if(verticesAreEqual(vertices1,vertices2))
            return true;

        if (haveIntersectingSegmentsOrVerticesAreIncluded(vertices1, vertices2))
            return true;

        return false;
    }

    private static void checkIfCollidingItself(Collidable collidable1, Collidable collidable2){
        if(collidable1==collidable2)
            System.out.print("Error: Checking Collision with itself");
    }

    private static boolean bothAreLegalPolygons(ArrayList<Double2D> vertices1, ArrayList<Double2D> vertices2){
        if(vertices1==null || vertices2==null)
            return false;
        if(vertices1.size()<3 || vertices2.size()<3)
            return false;
        return true;
    }

    private static ArrayList<Double2D> rotateVertices(ArrayList<Double2D> vertices, Double rotation) {
        ArrayList<Double2D> rotatedVertices = new ArrayList<>();

        for (Double2D vertex : vertices)
            rotatedVertices.add(new Double2D((vertex.getX()*Math.cos(rotation))-(vertex.getY()*Math.sin(rotation)),
                                             (vertex.getY()*Math.cos(rotation))-(vertex.getX()*Math.sin(rotation))));

        return rotatedVertices;
    }

    private static ArrayList<Double2D> translateVertices(ArrayList<Double2D> vertices, Double2D offset) {
        ArrayList<Double2D> translatedVertices = new ArrayList<>();

        for (Double2D vertex : vertices)
            translatedVertices.add(new Double2D(vertex.getX() + offset.getX(), vertex.getY() + offset.getY()));

        return translatedVertices;
    }

    private static boolean verticesAreEqual(ArrayList<Double2D> vertices1, ArrayList<Double2D> vertices2){

        Collections.sort(vertices1);
        Collections.sort(vertices2);

        return vertices1.equals(vertices2);
    }

    private static boolean haveIntersectingSegmentsOrVerticesAreIncluded(ArrayList<Double2D> vertices1,ArrayList<Double2D> vertices2){
        for (int i=0;i<vertices1.size();i++){
            for (int e=0;e<vertices2.size();e++){

                Double2D segment1point1;
                Double2D segment1point2;
                Double2D segment2point1;
                Double2D segment2point2;

                segment1point1 = vertices1.get(i);
                if (i == vertices1.size()-1)
                    segment1point2 = vertices1.get(0);
                else
                    segment1point2 = vertices1.get(i+1);

                segment2point1 = vertices2.get(e);
                if (e == vertices2.size()-1)
                    segment2point2 = vertices2.get(0);
                else
                    segment2point2 = vertices2.get(e+1);

                if (pointIsIncluded(vertices1.get(i),segment2point1,segment2point2))
                    return true;

                if (intersect(segment1point1,segment1point2,segment2point1,segment2point2))
                    return true;
            }
        }
        return false;
    }

    private static boolean pointIsIncluded(Double2D pointToCheck, Double2D segmentPoint1, Double2D segmentPoint2){
        int crossPoints =0;

        if (pointToCheck.getY()>Math.min(segmentPoint1.getY(),segmentPoint2.getY()) &&
                pointToCheck.getY()<Math.max(segmentPoint1.getY(),segmentPoint2.getY())){

            double xOfSegmentWithGivenY = segmentPoint1.getX()+
                    (((pointToCheck.getY()-segmentPoint1.getY())*(segmentPoint2.getX()-segmentPoint1.getX()))/
                            (segmentPoint2.getY()-segmentPoint2.getY()));

            if(xOfSegmentWithGivenY>=pointToCheck.getX())
                crossPoints++;
        }


        return (crossPoints % 2 == 1);
    }

    private static boolean intersect(Double2D segment1point1,Double2D segment1point2,Double2D segment2point1,Double2D segment2point2){
        int orientation1 = checkOrientation(segment1point1,segment1point2,segment2point1);
        int orientation2 = checkOrientation(segment1point1,segment1point2,segment2point2);
        int orientation3 = checkOrientation(segment2point1,segment2point2,segment1point1);
        int orientation4 = checkOrientation(segment2point1,segment2point2,segment1point2);

        if (orientationGeneralMethod(orientation1, orientation2, orientation3, orientation4))
            return true;

        if (orientationCheckSpecialCase(orientation1, orientation2, orientation3, orientation4))
            if(checkParallelIntersect(segment1point1,segment1point2,segment2point1,segment2point2))
                return true;

        return false;
    }

    private static boolean orientationGeneralMethod(int orientation1, int orientation2, int orientation3, int orientation4){
        return (orientation1!=orientation2)&&(orientation3!=orientation4);
    }

    private static boolean orientationCheckSpecialCase(int orientation1, int orientation2, int orientation3, int orientation4){
        return (orientation1==0 &&
                orientation1==orientation2 &&
                orientation1==orientation3 &&
                orientation1==orientation4);
    }

    private static boolean checkParallelIntersect(Double2D segment1point1, Double2D segment1point2, Double2D segment2point1, Double2D segment2point2){
        return (((segment1point1.getX()>=Math.min(segment2point1.getX(), segment2point2.getX()) &&
                  segment1point1.getX()<=Math.max(segment2point1.getX(), segment2point2.getX()))
                  ||
                 (segment1point2.getX()>=Math.min(segment2point1.getX(), segment2point2.getX()) &&
                  segment1point2.getX()<=Math.max(segment2point1.getX(), segment2point2.getX())))
                  &&
                ((segment1point1.getY()>=Math.min(segment2point1.getY(), segment2point2.getY()) &&
                  segment1point1.getY()<=Math.max(segment2point1.getY(), segment2point2.getY()))
                  ||
                 (segment1point2.getY()>=Math.min(segment2point1.getY(), segment2point2.getY()) &&
                  segment1point2.getY()<=Math.max(segment2point1.getY(), segment2point2.getY()))));
    }

    private static int checkOrientation(Double2D point1, Double2D point2, Double2D point3){
        double slopeRatio = (point2.getY() - point1.getY())*(point3.getX() - point2.getX())
                - (point3.getY() - point2.getY())*(point2.getX() - point1.getX());

        if (slopeRatio == 0.0)
            return 0;
        return (slopeRatio > 0) ? 1 : -1;
    }
}

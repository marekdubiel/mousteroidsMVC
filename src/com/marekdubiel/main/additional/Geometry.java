package com.marekdubiel.main.additional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.marekdubiel.main.additional.Calculate.aproximateDouble2Ds;

public class Geometry {

    public static ArrayList<Double2D> translateVertices(ArrayList<Double2D> vertices, Double2D offset) {
        ArrayList<Double2D> translatedVertices = new ArrayList<>();

        for (Double2D vertex : vertices)
            translatedVertices.add(new Double2D(vertex.getX() + offset.getX(), vertex.getY() + offset.getY()));
        return translatedVertices;
    }

    public static ArrayList<Double2D> rotateVertices(ArrayList<Double2D> vertices, Double rotation) {
        ArrayList<Double2D> rotatedVertices = new ArrayList<>();

        for (Double2D vertex : vertices) {
            double rotatedX = (vertex.getX()*Math.cos(Math.toRadians(rotation)))-(vertex.getY()*Math.sin(Math.toRadians(rotation)));
            double rotatedY = (vertex.getY()*Math.cos(Math.toRadians(rotation)))+(vertex.getX()*Math.sin(Math.toRadians(rotation)));

            rotatedVertices.add(aproximateDouble2Ds(new Double2D(rotatedX,rotatedY),5));
        }
        return rotatedVertices;
    }

    public static ArrayList<Double2D> scaleVertices(ArrayList<Double2D> vertices, Double scale) {
        ArrayList<Double2D> scaledVertices = new ArrayList<>();

        for (Double2D vertex : vertices) {
            double scaledX = vertex.getX() * scale;
            double scaledY = vertex.getY() * scale;
            scaledVertices.add(aproximateDouble2Ds(new Double2D(scaledX,scaledY),5));
        }

        return scaledVertices;
    }

    public static boolean checkCollision(Collidable collidable1, Collidable collidable2) throws Exception{

        checkIfCollidingItself(collidable1,collidable2);

        ArrayList<Double2D> vertices1 = collidable1.getBoundingVertices();
        ArrayList<Double2D> vertices2 = collidable2.getBoundingVertices();

        if (!VerticesMakeLegalPolygon(vertices1,vertices2))
            return false;
        vertices1 = scaleVertices(vertices1, collidable1.getScale());
        vertices2 = scaleVertices(vertices2, collidable2.getScale());

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

    public static void checkIfCollidingItself(Collidable collidable1, Collidable collidable2) throws Exception{
        if(collidable1==collidable2)
            throw new Exception("Error: Checking Collision with itself");
    }

    public static boolean VerticesMakeLegalPolygon(ArrayList<Double2D> vertices1, ArrayList<Double2D> vertices2){
        if(vertices1==null || vertices2==null)
            return false;
        if(vertices1.size()<3 || vertices2.size()<3)
            return false;
        return true;
    }


    public static boolean verticesAreEqual(ArrayList<Double2D> vertices1, ArrayList<Double2D> vertices2){

        List<Double2D> verticesToSort1 = new ArrayList<>(vertices1);
        List<Double2D> verticesToSort2 = new ArrayList<>(vertices2);
        Collections.sort(verticesToSort1);
        Collections.sort(verticesToSort2);

        return verticesToSort1.equals(verticesToSort2);
    }

    public static boolean haveIntersectingSegmentsOrVerticesAreIncluded(ArrayList<Double2D> vertices1,ArrayList<Double2D> vertices2){
        Double2D segment1point1;
        Double2D segment1point2;
        Double2D segment2point1;
        Double2D segment2point2;


        for (int i=0;i<vertices1.size();i++){
            segment1point1 = vertices1.get(i);
            if (i == vertices1.size()-1)
                segment1point2 = vertices1.get(0);
            else
                segment1point2 = vertices1.get(i+1);
            int segmentsDirectlyOnRightSideOfVertex = 0;

            for (int e=0;e<vertices2.size();e++){
                segment2point1 = vertices2.get(e);
                if (e == vertices2.size()-1)
                    segment2point2 = vertices2.get(0);
                else
                    segment2point2 = vertices2.get(e+1);

                if (pointIsDirectlyOnLeftSideOfSegment(vertices1.get(i),segment2point1,segment2point2))
                    segmentsDirectlyOnRightSideOfVertex ++;

                if (intersects(segment1point1,segment1point2,segment2point1,segment2point2))
                    return true;
            }

            if (segmentsDirectlyOnRightSideOfVertex % 2 ==1)
                return true;

        }
        return false;
    }

    public static boolean pointIsDirectlyOnLeftSideOfSegment(Double2D pointToCheck, Double2D segmentPoint1, Double2D segmentPoint2){

        if (pointToCheck.getY()>Math.min(segmentPoint1.getY(),segmentPoint2.getY()) &&
                pointToCheck.getY()<Math.max(segmentPoint1.getY(),segmentPoint2.getY())){

            double xOfSegmentWithGivenY = segmentPoint1.getX()+
                    (((pointToCheck.getY()-segmentPoint1.getY())*(segmentPoint2.getX()-segmentPoint1.getX()))/
                            (segmentPoint2.getY()-segmentPoint1.getY()));

            if(xOfSegmentWithGivenY>=pointToCheck.getX())
                return true;
        }
        return false;
    }

    public static boolean intersects(Double2D segment1point1,Double2D segment1point2,Double2D segment2point1,Double2D segment2point2){
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

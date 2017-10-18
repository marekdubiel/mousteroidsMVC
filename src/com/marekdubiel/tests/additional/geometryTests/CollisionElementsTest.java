package com.marekdubiel.tests.additional.geometryTests;

import com.marekdubiel.main.additional.Double2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.marekdubiel.main.additional.Geometry.*;
import static org.junit.jupiter.api.Assertions.*;

public class CollisionElementsTest {
    ArrayList<Double2D> polygonVertices1;
    ArrayList<Double2D> polygonVertices2;

    DummyPolygon polygon1;
    DummyPolygon polygon2;

    @Test
    void checkingCollisionWithItselfShouldThrowException(){
        polygonVertices1 = new ArrayList<>();

        polygonVertices1.add(new Double2D(1, 1));

        polygon1 = new DummyPolygon(polygonVertices1, new Double2D(0, 0), 0,1);

        Throwable exception = assertThrows(Exception.class, () -> {
            checkIfCollidingItself(polygon1,polygon1);
        });
        assertEquals("Error: Checking Collision with itself", exception.getMessage());
    }

    @Test
    void nullPollygonIsIllegal() {

        polygonVertices1 = new ArrayList<>();


        polygonVertices1.add(new Double2D(-4, 2));
        polygonVertices1.add(new Double2D(-3, 3));
        polygonVertices1.add(new Double2D(-2, 4));
        polygonVertices1.add(new Double2D(-1, 45));

        assertFalse(VerticesMakeLegalPolygon(polygonVertices1,polygonVertices2));
    }

    @Test
    void polygonWith2VerticesIsIllegal() {

        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(-4, 2));
        polygonVertices1.add(new Double2D(-3, 3));
        polygonVertices1.add(new Double2D(-2, 4));
        polygonVertices1.add(new Double2D(-1, 45));

        polygonVertices1.add(new Double2D(-5, 6));
        polygonVertices1.add(new Double2D(6, -5));

        assertFalse(VerticesMakeLegalPolygon(polygonVertices1,polygonVertices2));
    }

    @Test
    void equalVerticesShouldBeEqualOrderDoesNotMatter() {
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(-4, -76));
        polygonVertices1.add(new Double2D(213, -12));
        polygonVertices1.add(new Double2D(12, 34));
        polygonVertices1.add(new Double2D(65, 45));

        polygonVertices2.add(new Double2D(12, 34));
        polygonVertices2.add(new Double2D(213, -12));
        polygonVertices2.add(new Double2D(65, 45));
        polygonVertices2.add(new Double2D(-4, -76));

        assertTrue(verticesAreEqual(polygonVertices1,polygonVertices2));
    }

    @Test
    void vertexDirectlyOnLeftSideIsIndeedDirectlyOnLeftSide() {
        Double2D checkedPoint1 = new Double2D(-2,11);

        Double2D segmentPoint1 = new Double2D(4,10);
        Double2D segmentPoint2 = new Double2D(2,12);

        assertTrue(pointIsDirectlyOnLeftSideOfSegment(checkedPoint1,segmentPoint1,segmentPoint2));
    }

    @Test
    void vertexDirectlyOnRightSideIsNotDirectlyOnLeftSide() {
        Double2D checkedPoint1 = new Double2D(4,11);

        Double2D segmentPoint1 = new Double2D(5,10);
        Double2D segmentPoint2 = new Double2D(2,12);

        assertFalse(pointIsDirectlyOnLeftSideOfSegment(checkedPoint1,segmentPoint1,segmentPoint2));
    }

    @Test
    void vertexDirectlyOnLeftSideOfVertexDoesNotCount() {
        Double2D checkedPoint1 = new Double2D(-2,11);

        Double2D segmentPoint1 = new Double2D(5,11);
        Double2D segmentPoint2 = new Double2D(2,11);

        assertFalse(pointIsDirectlyOnLeftSideOfSegment(checkedPoint1,segmentPoint1,segmentPoint2));
    }

    @Test
    void vertexBelowOrAboveSegmentIsNotDirectlyOnLeftSide() {
        Double2D checkedPoint1 = new Double2D(-2,-4);
        Double2D checkedPoint2 = new Double2D(3,13);

        Double2D segmentPoint1 = new Double2D(4,10);
        Double2D segmentPoint2 = new Double2D(3,12);

        assertFalse(pointIsDirectlyOnLeftSideOfSegment(checkedPoint1,segmentPoint1,segmentPoint2));
        assertFalse(pointIsDirectlyOnLeftSideOfSegment(checkedPoint2,segmentPoint1,segmentPoint2));
    }

    @Test
    void crossingSegmentsIntersect() {
        Double2D segment1Point1 = new Double2D(-5,-4);
        Double2D segment1Point2 = new Double2D(12,9);

        Double2D segment2Point1 = new Double2D(-3,14);
        Double2D segment2Point2 = new Double2D(6,-2);

        assertTrue(intersects(segment1Point1,segment1Point2,segment2Point1,segment2Point2));
    }

    @Test
    void notCrossingSegmentsDoNotIntersect() {
        Double2D segment1Point1 = new Double2D(-5,-4);
        Double2D segment1Point2 = new Double2D(12,9);

        Double2D segment2Point1 = new Double2D(-3,14);
        Double2D segment2Point2 = new Double2D(-2,9);

        assertFalse(intersects(segment1Point1,segment1Point2,segment2Point1,segment2Point2));
    }

    @Test
    void overlapingSegmentsIntersect(){
        Double2D segment1Point1 = new Double2D(-5,-5);
        Double2D segment1Point2 = new Double2D(3,3);

        Double2D segment2Point1 = new Double2D(-3,-3);
        Double2D segment2Point2 = new Double2D(16,-16);

        assertTrue(intersects(segment1Point1,segment1Point2,segment2Point1,segment2Point2));
    }

    @Test
    void equalSegmentsIntersect(){
        Double2D segment1Point1 = new Double2D(-5,-4);
        Double2D segment1Point2 = new Double2D(12,9);

        Double2D segment2Point1 = new Double2D(-5,-4);
        Double2D segment2Point2 = new Double2D(12,9);

        assertTrue(intersects(segment1Point1,segment1Point2,segment2Point1,segment2Point2));
    }

    @Test
    void overlapingPolygonsHaveIntersectingSegments(){
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(-4, 3));
        polygonVertices1.add(new Double2D(2, 0));
        polygonVertices1.add(new Double2D(-5, -4));

        polygonVertices2.add(new Double2D(4, 7));
        polygonVertices2.add(new Double2D(3, -3));
        polygonVertices2.add(new Double2D(-1, 0));

        assertTrue(haveIntersectingSegmentsOrVerticesAreIncluded(polygonVertices1,polygonVertices2));
    }

    @Test
    void separatePolygonsHaveIntersectingSegments(){
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(-4, 3));
        polygonVertices1.add(new Double2D(0, -1));
        polygonVertices1.add(new Double2D(-5, -4));

        polygonVertices2.add(new Double2D(4, 7));
        polygonVertices2.add(new Double2D(3, -3));
        polygonVertices2.add(new Double2D(0, 2));

        assertFalse(haveIntersectingSegmentsOrVerticesAreIncluded(polygonVertices1,polygonVertices2));
    }

    @Test
    void smallerPolygonInsideLargerIsIncluded(){
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(-1, 1));
        polygonVertices1.add(new Double2D(2, 0));
        polygonVertices1.add(new Double2D(-2, -2));

        polygonVertices2.add(new Double2D(-4, 7));
        polygonVertices2.add(new Double2D(3, -1));
        polygonVertices2.add(new Double2D(-7, -9));

        assertTrue(haveIntersectingSegmentsOrVerticesAreIncluded(polygonVertices1,polygonVertices2));
    }

    @Test
    void BiggerPolygonIsNotIncludedInSmaller(){
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(-1, 1));
        polygonVertices1.add(new Double2D(2, 0));
        polygonVertices1.add(new Double2D(-2, -2));

        polygonVertices2.add(new Double2D(-4, 7));
        polygonVertices2.add(new Double2D(3, -1));
        polygonVertices2.add(new Double2D(-7, -9));

        assertFalse(haveIntersectingSegmentsOrVerticesAreIncluded(polygonVertices2,polygonVertices1));
    }
}


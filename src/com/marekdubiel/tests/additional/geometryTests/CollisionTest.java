package com.marekdubiel.tests.additional.geometryTests;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.additional.Geometry;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CollisionTest {
    ArrayList<Double2D> polygonVertices1;
    ArrayList<Double2D> polygonVertices2;

    DummyPolygon polygon1;
    DummyPolygon polygon2;


    @Test
    void overlappingIdenticalPolygonsShouldCollide() throws Exception{
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(-5, -5));
        polygonVertices1.add(new Double2D(5, -5));
        polygonVertices1.add(new Double2D(5, 5));
        polygonVertices1.add(new Double2D(-5, 5));

        polygonVertices2.add(new Double2D(-5, -5));
        polygonVertices2.add(new Double2D(5, -5));
        polygonVertices2.add(new Double2D(-5, 5));
        polygonVertices2.add(new Double2D(5, 5));

        polygon1 = new DummyPolygon(polygonVertices1, new Double2D(0, 0), 0,1);
        polygon2 = new DummyPolygon(polygonVertices2, new Double2D(0, 0), 0,1);

        assertTrue(Geometry.checkCollision(polygon1, polygon2));
    }

    @Test
    void smallerPolygonInsideLargerShouldCollide() throws Exception{
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(1, 1));
        polygonVertices1.add(new Double2D(1, -1));
        polygonVertices1.add(new Double2D(-1, -1));
        polygonVertices1.add(new Double2D(-1, 1));

        polygonVertices2.add(new Double2D(5, 5));
        polygonVertices2.add(new Double2D(5, -5));
        polygonVertices2.add(new Double2D(-5, -5));
        polygonVertices2.add(new Double2D(-5, 5));

        polygon1 = new DummyPolygon(polygonVertices1, new Double2D(0, 0), 0,1);
        polygon2 = new DummyPolygon(polygonVertices2, new Double2D(0, 0), 0,1);

        assertTrue(Geometry.checkCollision(polygon1, polygon2));
    }

    @Test
    void BiggerPolygonShouldNotCareForSmallerOnesInside() throws Exception{
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(1, 1));
        polygonVertices1.add(new Double2D(1, -1));
        polygonVertices1.add(new Double2D(-1, -1));
        polygonVertices1.add(new Double2D(-1, 1));

        polygonVertices2.add(new Double2D(5, 5));
        polygonVertices2.add(new Double2D(5, -5));
        polygonVertices2.add(new Double2D(-5, -5));
        polygonVertices2.add(new Double2D(-5, 5));

        polygon1 = new DummyPolygon(polygonVertices1, new Double2D(0, 0), 0,1);
        polygon2 = new DummyPolygon(polygonVertices2, new Double2D(0, 0), 0,1);

        assertFalse(Geometry.checkCollision(polygon2, polygon1));
    }

    @Test
    void ShiftedIdenticalPolygonsShouldNotCollide() throws Exception {
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(5, 5));
        polygonVertices1.add(new Double2D(5, -5));
        polygonVertices1.add(new Double2D(-5, -5));
        polygonVertices1.add(new Double2D(-5, 5));

        polygonVertices2.add(new Double2D(5, 5));
        polygonVertices2.add(new Double2D(5, -5));
        polygonVertices2.add(new Double2D(-5, -5));
        polygonVertices2.add(new Double2D(-5, 5));

        polygon1 = new DummyPolygon(polygonVertices1,new Double2D(5.1,0),0,1);
        polygon2 = new DummyPolygon(polygonVertices2,new Double2D(-5.1,0),0,1);

        assertFalse(Geometry.checkCollision(polygon1,polygon2));
    }

    @Test
    void ShiftedAndScaledUpIdenticalPolygonsShouldCollide() throws Exception {
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(5, 5));
        polygonVertices1.add(new Double2D(5, -5));
        polygonVertices1.add(new Double2D(-5, -5));
        polygonVertices1.add(new Double2D(-5, 5));

        polygonVertices2.add(new Double2D(5, 5));
        polygonVertices2.add(new Double2D(5, -5));
        polygonVertices2.add(new Double2D(-5, -5));
        polygonVertices2.add(new Double2D(-5, 5));

        polygon1 = new DummyPolygon(polygonVertices1,new Double2D(5.1,0),0,2);
        polygon2 = new DummyPolygon(polygonVertices2,new Double2D(-5.1,0),0,2);

        assertTrue(Geometry.checkCollision(polygon1,polygon2));
    }

    @Test
    void ShiftedAndRotatedIdenticalPolygonsShouldCollide() throws Exception{
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(5, 5));
        polygonVertices1.add(new Double2D(5, -5));
        polygonVertices1.add(new Double2D(-5, -5));
        polygonVertices1.add(new Double2D(-5, 5));

        polygonVertices2.add(new Double2D(5, 5));
        polygonVertices2.add(new Double2D(5, -5));
        polygonVertices2.add(new Double2D(-5, -5));
        polygonVertices2.add(new Double2D(-5, 5));

        polygon1 = new DummyPolygon(polygonVertices1,new Double2D(5.1,0),45,1);
        polygon2 = new DummyPolygon(polygonVertices2,new Double2D(-5.1,0),45,1);

        assertTrue(Geometry.checkCollision(polygon1,polygon2));
    }

    @Test
    void ShiftedRotatedAndScaledDownIdenticalPolygonsShouldNotCollide() throws Exception {
        polygonVertices1 = new ArrayList<>();
        polygonVertices2 = new ArrayList<>();

        polygonVertices1.add(new Double2D(5, 5));
        polygonVertices1.add(new Double2D(5, -5));
        polygonVertices1.add(new Double2D(-5, -5));
        polygonVertices1.add(new Double2D(-5, 5));

        polygonVertices2.add(new Double2D(5, 5));
        polygonVertices2.add(new Double2D(5, -5));
        polygonVertices2.add(new Double2D(-5, -5));
        polygonVertices2.add(new Double2D(-5, 5));

        polygon1 = new DummyPolygon(polygonVertices1, new Double2D(5.1, 0), 45, 0.2);
        polygon2 = new DummyPolygon(polygonVertices2, new Double2D(-5.1, 0), 45, 0.2);

        assertFalse(Geometry.checkCollision(polygon1, polygon2));
    }
}
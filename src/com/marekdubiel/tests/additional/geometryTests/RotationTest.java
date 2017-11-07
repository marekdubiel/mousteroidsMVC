package com.marekdubiel.tests.additional.geometryTests;

import com.marekdubiel.main.additional.Double2D;

import org.junit.jupiter.api.Test;

import static com.marekdubiel.main.additional.Geometry.rotateVertices;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;

public class RotationTest {
    ArrayList<Double2D> testedVertices;
    ArrayList<Double2D> controlVertices;

    @Test
    void rotatedVerticesShouldEqualControlVertices() {
        double rotateValue = 90.0;

        testedVertices = new ArrayList<>();
        controlVertices = new ArrayList<>();

        testedVertices.add(new Double2D(-5, -10));
        testedVertices.add(new Double2D(5, -10));
        testedVertices.add(new Double2D(5, 10));
        testedVertices.add(new Double2D(-5, 10));

        controlVertices.add(new Double2D(10, -5));
        controlVertices.add(new Double2D(10, 5));
        controlVertices.add(new Double2D(-10, 5));
        controlVertices.add(new Double2D(-10, -5));

        assertIterableEquals(rotateVertices(testedVertices,rotateValue),controlVertices);
    }

    @Test
    void VerticesShouldRotateAroundOriginPointOutsidePolygon() {
        double rotateValue = 270.0;

        testedVertices = new ArrayList<>();
        controlVertices = new ArrayList<>();

        testedVertices.add(new Double2D(22, 11));
        testedVertices.add(new Double2D(25, 5));
        testedVertices.add(new Double2D(17, 6));

        controlVertices.add(new Double2D(11, -22));
        controlVertices.add(new Double2D(5, -25));
        controlVertices.add(new Double2D(6, -17));

        assertIterableEquals(rotateVertices(testedVertices,rotateValue),controlVertices);
    }

    @Test
    void verticesRotatedBy0ShouldEqualItself() {
        double rotateValue = 0;

        testedVertices = new ArrayList<>();

        testedVertices.add(new Double2D(-6, -2));
        testedVertices.add(new Double2D(5, -8));
        testedVertices.add(new Double2D(7, 0));
        testedVertices.add(new Double2D(5, 16));
        testedVertices.add(new Double2D(-2, 2));

        assertIterableEquals(rotateVertices(testedVertices,rotateValue),testedVertices);
    }
}

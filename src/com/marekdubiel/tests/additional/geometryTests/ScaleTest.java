package com.marekdubiel.tests.additional.geometryTests;
import com.marekdubiel.main.additional.Double2D;

import org.junit.jupiter.api.Test;

import static com.marekdubiel.main.additional.Geometry.scaleVertices;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;

public class ScaleTest {
    ArrayList<Double2D> testedVertices;
    ArrayList<Double2D> controlVertices;

    @Test
    void verticesScaledByGivenValueShouldEqualControlVertices(){
        Double scaleValue = 2.5;
        testedVertices = new ArrayList<>();
        controlVertices = new ArrayList<>();


        testedVertices.add(new Double2D(-6, -10));
        testedVertices.add(new Double2D(8, -10));
        testedVertices.add(new Double2D(4, 8));
        testedVertices.add(new Double2D(-2, 12));

        controlVertices.add(new Double2D(-15, -25));
        controlVertices.add(new Double2D(20, -25));
        controlVertices.add(new Double2D(10, 20));
        controlVertices.add(new Double2D(-5, 30));

        assertIterableEquals(scaleVertices(testedVertices,scaleValue),controlVertices);
    }

    @Test
    void verticesScaledBy1ShouldEqualItself(){
        testedVertices = new ArrayList<>();

        testedVertices.add(new Double2D(-5, -9));
        testedVertices.add(new Double2D(5, -10));
        testedVertices.add(new Double2D(5, 7));
        testedVertices.add(new Double2D(-5, 12));

        assertIterableEquals(scaleVertices(testedVertices, 1.0),testedVertices);
    }

    @Test
    void verticesScaledBy0ShouldAllMoveToPoint0_0(){
        testedVertices = new ArrayList<>();
        controlVertices = new ArrayList<>();

        testedVertices.add(new Double2D(-11, -9));
        testedVertices.add(new Double2D(8, -10));
        testedVertices.add(new Double2D(16, 7));
        testedVertices.add(new Double2D(-9, 12));

        controlVertices.add(new Double2D(0, 0));
        controlVertices.add(new Double2D(0, 0));
        controlVertices.add(new Double2D(0, 0));
        controlVertices.add(new Double2D(0, 0));

        assertIterableEquals(scaleVertices(testedVertices, 0.0),controlVertices);
    }
}

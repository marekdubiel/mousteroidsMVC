package com.marekdubiel.tests.additional.geometryTests;

import com.marekdubiel.main.additional.Double2D;
import com.marekdubiel.main.additional.Geometry;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class TransformTest {
    ArrayList<Double2D> testedVertices;
    ArrayList<Double2D> controlVertices;

    @Test
    void translatedVerticesShouldEqualControlVertices() {
        Double2D translateValue = new Double2D(12,-23);

        testedVertices = new ArrayList<>();
        controlVertices = new ArrayList<>();

        testedVertices.add(new Double2D(-5, -5));
        testedVertices.add(new Double2D(5, -5));
        testedVertices.add(new Double2D(5, 5));
        testedVertices.add(new Double2D(-5, 5));

        controlVertices.add(new Double2D(7, -28));
        controlVertices.add(new Double2D(17, -28));
        controlVertices.add(new Double2D(17, -18));
        controlVertices.add(new Double2D(7, -18));

        assertIterableEquals(Geometry.translateVertices(testedVertices,translateValue),controlVertices);
    }

    @Test
    void verticesTranslatedBy0ShouldEqualItself() {
        Double2D translateValue = new Double2D(0,0);

        testedVertices = new ArrayList<>();
        controlVertices = new ArrayList<>();

        testedVertices.add(new Double2D(-5, -5));
        testedVertices.add(new Double2D(5, -5));
        testedVertices.add(new Double2D(5, 5));
        testedVertices.add(new Double2D(-5, 5));

        assertIterableEquals(Geometry.translateVertices(testedVertices,translateValue),testedVertices);
    }
}

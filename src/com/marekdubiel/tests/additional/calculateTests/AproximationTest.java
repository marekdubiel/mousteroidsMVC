package com.marekdubiel.tests.additional.calculateTests;

import com.marekdubiel.main.additional.Double2D;
import org.junit.jupiter.api.Test;

import static com.marekdubiel.main.additional.Calculate.aproximateDouble2Ds;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AproximationTest {

    @Test
    void AproximatedValueShouldEqualControllValueWithLessDecimalPoints() {
        Double2D testedValue = new Double2D(2.0193, 5.0212111);
        Double2D controllValue = new Double2D(2.02, 5.02);
        assertEquals(aproximateDouble2Ds(testedValue,2),controllValue);
    }

    @Test
    void AproximatedValueShouldEqualControllValueWithMoreDecimalPoints() {
        Double2D testedValue = new Double2D(-2, -5);
        Double2D controllValue = new Double2D(-2.0, -5.0);
        assertEquals(aproximateDouble2Ds(testedValue,4),controllValue);
    }

    @Test
    void AproximatedValueShouldEqualControllValueWithNoDecimalPoints() {
        Double2D testedValue = new Double2D(2.0193, 7.0212111);
        Double2D controllValue = new Double2D(2.0, 7.0);
        assertEquals(aproximateDouble2Ds(testedValue,0),controllValue);
    }
}


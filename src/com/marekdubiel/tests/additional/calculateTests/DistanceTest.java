package com.marekdubiel.tests.additional.calculateTests;

import com.marekdubiel.main.additional.Double2D;
import org.junit.jupiter.api.Test;

import static com.marekdubiel.main.additional.Calculate.distance;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceTest {
    @Test
    void DistanceToPointOnSameHorizontalLineAsOriginShouldEqualAbsoluteValueOfx1Minusx2() {
        assertEquals(39,distance(new Double2D(-16,43), new Double2D(23,43)));
        assertEquals(39,distance(new Double2D(23,43), new Double2D(-16,43)));
    }

    @Test
    void DistanceToPointOnSameverticalLineAsOriginShouldEqualAbsoluteValueOfx1Minusx2() {
        assertEquals(20,distance(new Double2D(-16,43), new Double2D(-16,23)));
        assertEquals(20,distance(new Double2D(-16,23), new Double2D(-16,43)));
    }

    @Test
    void DistanceFromPoint0_0ToPoint4_3ShouldEqual5() {
        assertEquals(5,distance(new Double2D(0,0), new Double2D(4,3)));
    }

    @Test
    void DistanceToItselfShoulEqual0() {
        assertEquals(0,distance(new Double2D(-16,43), new Double2D(-16,43)));
    }
}

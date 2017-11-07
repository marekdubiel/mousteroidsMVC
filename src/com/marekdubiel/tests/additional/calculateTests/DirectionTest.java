package com.marekdubiel.tests.additional.calculateTests;

import com.marekdubiel.main.additional.Double2D;
import org.junit.jupiter.api.Test;

import static com.marekdubiel.main.additional.Calculate.direction;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {
    @Test
    void DirectionToTargetDirectlyAboveOriginShouldEqual90Degrees() {

        assertEquals(90, direction(new Double2D(-16, 43), new Double2D(-16,976)));
    }

    @Test
    void DirectionToTargetDirectlyBelowOriginShouldEqual270Degrees() {

        assertEquals(270, direction(new Double2D(-16, 43), new Double2D(-16,-71)));
    }

    @Test
    void DirectionToTargetDirectlyRightToOriginShouldEqual0Degrees() {

        assertEquals(0, direction(new Double2D(-16, 43), new Double2D(93,43)));
    }

    @Test
    void DirectionToTargetDirectlyLeftToOriginShouldEqual180Degrees() {

        assertEquals(180, direction(new Double2D(-16, 43), new Double2D(-86,43)));
    }

    @Test
    void DirectionToTargetDownAndlyLeftToOriginShouldEqual225Degrees() {

        assertEquals(225, direction(new Double2D(-16, 43), new Double2D(-59,0)));
    }

    @Test
    void DirectionToTargetEqualToOriginShouldEqual0Degrees() {

        assertEquals(0, direction(new Double2D(-16, 43), new Double2D(-16,43)));
    }
}

package ru.anfisa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {


    @Test
    void canCalculateArea() {
        var s = new Square(5.0);
        var result = s.area();
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        var result = new Square(5.0).perimeter();
        Assertions.assertEquals(20, result);
    }

    @Test
    void canCalculateSquareWithNegativeSide() {
        try {
            new Square(-1);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }

    }
}

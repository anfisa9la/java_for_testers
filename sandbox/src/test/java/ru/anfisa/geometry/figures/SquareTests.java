package ru.anfisa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea() {
        var result = Square.area(5);
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCalculatePerimeter() {
        var result = Square.perimeter(5);
        Assertions.assertEquals(20, result);
    }
}

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

    @Test
    void testEquality() {
        var s1 = new Square(5);
        var s2 = new Square(5);
        Assertions.assertEquals(s1, s2);
    }

    @Test
    void testNonEquality() {
        var s1 = new Square(5);
        var s2 = new Square(9);
        Assertions.assertNotEquals(s1, s2);
    }

    @Test
    void testEquality2() {
        var s1 = new Square(5);
        var s2 = new Square(5);
        Assertions.assertTrue(s1.equals(s2));
    }
}

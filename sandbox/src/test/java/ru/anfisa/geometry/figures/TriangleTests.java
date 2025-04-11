package ru.anfisa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {


    @Test
    void canCalculateArea1() {
        var t = new Triangle(5.0, 5.0, 5.0);
        var result = t.area();
        Assertions.assertEquals(11, result);
    }

    @Test
    void canCalculateArea2() {
        var t = new Triangle(3.0, 6.0, 6.0);
        var result = t.area();
        Assertions.assertEquals(9, result);
    }

    @Test
    void canCalculateArea3() {
        var t = new Triangle(3.0, 5.0, 6.0);
        var result = t.area();
        Assertions.assertEquals(7, result);
    }

    @Test
    void canCalculatePerimeter1() {
        var result = new Triangle(3.0, 5.0, 6.0).perimeter();
        Assertions.assertEquals(14, result);
    }

    @Test
    void canCalculatePerimeter2() {
        var result = new Triangle(5.0, 5.0, 5.0).perimeter();
        Assertions.assertEquals(15, result);
    }

    @Test
    void canCalculatePerimeter3() {
        var result = new Triangle(3.0, 6.0, 6.0).perimeter();
        Assertions.assertEquals(15, result);
    }

    @Test
    void canCheckTriangleInequality1() {
        try {
            new Triangle(1, 2, 10);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void canCheckTriangleInequality2() {
        try {
            new Triangle(16, 2, 1);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void canCheckTriangleInequality3() {
        try {
            new Triangle(6, 29, 1);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void testEquality1() {
        var t1 = new Triangle(5, 6, 1);
        var t2 = new Triangle(5, 1, 6);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality2() {
        var t1 = new Triangle(5, 6, 1);
        var t2 = new Triangle(1, 6, 5);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality3() {
        var t1 = new Triangle(5, 6, 1);
        var t2 = new Triangle(5, 6, 1);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality4() {
        var t1 = new Triangle(5, 6, 1);
        var t2 = new Triangle(6, 5, 1);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality5() {
        var t1 = new Triangle(5, 6, 1);
        var t2 = new Triangle(1, 5, 6);
        Assertions.assertEquals(t1, t2);
    }
}

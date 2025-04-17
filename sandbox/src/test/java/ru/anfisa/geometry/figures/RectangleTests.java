package ru.anfisa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void testEquality1() {
        var r1 = new Rectangle(5, 6);
        var r2 = new Rectangle(6, 5);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testEquality2() {
        var r1 = new Rectangle(5, 6);
        var r2 = new Rectangle(5, 6);
        Assertions.assertEquals(r1, r2);
    }
}

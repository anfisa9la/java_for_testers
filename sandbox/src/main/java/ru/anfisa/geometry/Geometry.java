package ru.anfisa.geometry;

import ru.anfisa.geometry.figures.Rectangle;
import ru.anfisa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(1.);
        Square.printSquareArea(2.0);
        Square.printSquareArea(3);

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7., 6.0);
    }

}

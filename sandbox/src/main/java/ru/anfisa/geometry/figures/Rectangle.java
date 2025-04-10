package ru.anfisa.geometry.figures;

import java.util.Objects;

public class Rectangle {

    public double side1;
    public double side2;

    public Rectangle(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public static void printRectangleArea(double side1, double side2) {
        System.out.println("Площадь прямоугольника со сторонами " + side1 + side2 + " = " + rectangleArea(side1, side2));
    }

    private static double rectangleArea(double side1, double side2) {
        return side1 * side2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(this.side1, rectangle.side1) == 0 && Double.compare(this.side2, rectangle.side2) == 0)
                || (Double.compare(this.side1, rectangle.side2) == 0 && Double.compare(this.side2, rectangle.side1) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side1, side2);
    }
}

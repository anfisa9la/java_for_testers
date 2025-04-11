package ru.anfisa.geometry.figures;

import java.util.Objects;

public class Triangle {

    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;

        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

        if (side1 > side2 + side3 || side2 > side1 + side3 || side3 > side2 + side1) {
            throw new IllegalArgumentException("Triangle inequality is violated");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.side1, triangle.side1) == 0
                && Double.compare(this.side2, triangle.side2) == 0
                && Double.compare(this.side3, triangle.side3) == 0)

                || (Double.compare(this.side1, triangle.side1) == 0
                && Double.compare(this.side2, triangle.side3) == 0
                && Double.compare(this.side3, triangle.side2) == 0)

                || (Double.compare(this.side1, triangle.side3) == 0
                && Double.compare(this.side2, triangle.side2) == 0
                && Double.compare(this.side3, triangle.side1) == 0)

                || (Double.compare(this.side1, triangle.side2) == 0
                && Double.compare(this.side2, triangle.side3) == 0
                && Double.compare(this.side3, triangle.side1) == 0)

                || (Double.compare(this.side1, triangle.side2) == 0
                && Double.compare(this.side2, triangle.side1) == 0
                && Double.compare(this.side3, triangle.side3) == 0)

                || (Double.compare(this.side1, triangle.side3) == 0
                && Double.compare(this.side2, triangle.side1) == 0
                && Double.compare(this.side3, triangle.side2) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side1, side2);
    }

    public double perimeter() {
        return this.side1 + this.side2 + this.side3;
    }

    public double area() {
        double p = perimeter() / 2;
        double s = Math.sqrt(p * (p - this.side1) * (p - this.side2) * (p - this.side3));

        return Math.round(s);
    }

}

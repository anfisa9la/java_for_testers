package ru.anfisa.geometry.figures;

public class Triangle {

    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
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

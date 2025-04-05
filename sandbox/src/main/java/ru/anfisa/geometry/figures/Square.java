package ru.anfisa.geometry.figures;

public class Square {
    public static void printSquareArea(double side) {
        String text = String.format(
                "Площадь квадрата со стороной %f = %f", side, area(side));
        System.out.println(text);
    }

    public static double area(double side) {
        return side * side;
    }

    public static double perimeter(double side) {
        return side * 4;
    }
}

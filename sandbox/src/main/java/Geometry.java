public class Geometry {
    public static void main(String[] args) {
        printSquareArea(1.);
        printSquareArea(2.0);
        printSquareArea(3);

        printRectangleArea(3.0, 5.0);
        printRectangleArea(7., 6.0);
    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоугольника со сторонами " + a + b + " = " + rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    static void printSquareArea(double side) {
        System.out.println("Площадь квадрата со стороной " + side + " = " + squareArea(side));
    }

    private static double squareArea(double side) {
        return side * side;
    }
}

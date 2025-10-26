import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Geometry {
    public static void main(String[] args) {
        try {
            // Принудительная установка кодировки вывода
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        printSquareArea(7.0);
        printSquareArea(5.0);
        printSquareArea(3.0);

        printRectangleArea(3.0, 5.0);
        printRectangleArea(1.0, 4.0);
        printRectangleArea(2.0, 3.0);

    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    static void printSquareArea(double side){
        System.out.println("Площадь квадрата со стороной " + side + " = " + squareArea(side));
    }

    private static double squareArea(double a) {
        return a * a;
    }
}
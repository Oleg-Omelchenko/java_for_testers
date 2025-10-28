package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

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
        Square.printSquareArea(7.0);
        Square.printSquareArea(5.0);
        Square.printSquareArea(3.0);

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(1.0, 4.0);
        Rectangle.printRectangleArea(2.0, 3.0);

    }

}
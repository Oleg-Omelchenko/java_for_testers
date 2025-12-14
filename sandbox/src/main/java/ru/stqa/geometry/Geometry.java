package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        try {
            // Принудительная установка кодировки вывода
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100));
        var squares = Stream.generate(randomSquare).limit(5);
        squares.peek(Square::printSquareArea).forEach(Square::printPerimeter);
    }
}
package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void isTriangle() {
        try {
            new Triangle(5.0, 3.0, 7.0);
            System.out.println("Pretty Triangle");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception);
        }
    }

    @Test
    void isNotTriangle() {
        try {
            new Triangle(15.0, 3.0, 7.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception);
        }
    }

    @Test
    void CalculateArea() {
        Assertions.assertEquals(6.0, new Triangle(5.0, 4.0, 3.0).area());
    }

    @Test
    void CalculatePerimeter() {
        Assertions.assertEquals(13.0, new Triangle(6.0, 3.0, 4.0).perimeter());
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(5.0, -3.0, 7.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception);
        }
    }
}
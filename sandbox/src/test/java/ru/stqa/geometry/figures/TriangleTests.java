package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void isTriangle() {
        Assertions.assertEquals(true, new Triangle(15.0, 11.0, 6.0).check());
    }

    @Test
    void isNotTriangle() {
        Assertions.assertEquals(false, new Triangle(5.0, 10.0, 16.0).check());
    }

    @Test
    void CalculateArea() {
        Assertions.assertEquals(6.0, new Triangle(5.0, 4.0, 3.0).area());
    }

    @Test
    void CalculatePerimeter() {
        Assertions.assertEquals(13.0, new Triangle(6.0, 3.0, 4.0).perimeter());
    }
}

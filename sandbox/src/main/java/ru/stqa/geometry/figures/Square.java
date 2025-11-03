package ru.stqa.geometry.figures;

public record Square(double side) {

    public static void printSquareArea(Square s){
        var text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.area());
        System.out.println(text);
    }

    public static double Area(double a) {
        return a * a;
    }


    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return this.side * 4;
    }
}

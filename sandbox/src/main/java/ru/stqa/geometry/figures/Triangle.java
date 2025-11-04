package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {
    public Triangle{
        if (a<0 || b<0 || c<0){
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if ((a+b)<=c || (a+c)<=b || (b+c)<=a) {
           throw new IllegalArgumentException("Triangle inequality is violated");
        }
    }

    public double area() {
        var P = perimeter() / 2;
        var S = Math.sqrt(P*(P-this.a)*(P-this.b)*(P-this.c));
        return S;
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }
}

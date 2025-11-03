package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {

    public boolean check() {
        return ((this.a + this.b) > this.c) && ((this.a + this.c) > this.b) && ((this.c + this.b) > this.a);
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

package com.github.komidawi;

public class Square {
    protected double a;

    public Square(double a) {
        this.a = a;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double area() {
        return getA() * getA();
    }

    public boolean isBigger(Square compared) {
        return compared.area() > this.area();
    }
}

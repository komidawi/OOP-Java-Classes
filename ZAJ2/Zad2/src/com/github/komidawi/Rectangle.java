package com.github.komidawi;

public class Rectangle extends Square {
    private double b;

    public Rectangle(double a, double b) {
        super(a);
        this.b = b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getB() {
        return b;
    }

    public double area() {
        return getA() * getB();
    }

    public boolean isBigger(Rectangle compared) {
        return compared.area() > this.area();
    }
}
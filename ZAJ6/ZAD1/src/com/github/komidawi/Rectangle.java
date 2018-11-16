package com.github.komidawi;

import java.awt.*;

public class Rectangle extends Square {

    private int b;

    public Rectangle(int x, int y, int a, int b) {
        super(x, y, a);

        validateLength(b);
        this.b = b;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public int getB() {
        return b;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(x, y, getA(), getB());
    }
}

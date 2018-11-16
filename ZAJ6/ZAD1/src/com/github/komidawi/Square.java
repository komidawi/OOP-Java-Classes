package com.github.komidawi;

import java.awt.*;

public class Square extends Shape {

    protected int a;

    public Square(int x, int y, int a) {
        super(x, y);

        validateLength(a);
        this.a = a;
    }

    @Override
    public String toString() {
        return "Square{" +
                "a=" + a +
                '}';
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(x, y, getA(), getA());
    }

    public int getA() {
        return a;
    }
}

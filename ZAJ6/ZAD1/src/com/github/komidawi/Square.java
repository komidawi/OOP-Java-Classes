package com.github.komidawi;

import java.awt.*;

public class Square extends Shape {

    protected int a;

    public Square(Point position, int a) {
        super(position);

        validateLength(a);
        this.a = a;
    }

    @Override
    public String toString() {
        return "Square{a=" + a + "}";
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(position.x, position.y, getA(), getA());
    }

    public int getA() {
        return a;
    }
}

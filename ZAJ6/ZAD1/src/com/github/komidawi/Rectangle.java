package com.github.komidawi;

import java.awt.*;
import java.security.UnrecoverableKeyException;

public class Rectangle extends Square {

    protected int b;

    public Rectangle(Point position, int a, int b) {
        super(position, a);

        validateLength(b);
        this.b = b;
    }

    @Override
    public String toString() {
        return "Rectangle{a=" + a + ", b=" + b + "}";
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getB() {
        return b;
    }

    @Override
    public boolean isWithinArea(Point point) {
        return point.x >= position.x && point.x <= position.x + getA()
                && point.y >= position.y && point.y <= position.y + getB();
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(position.x, position.y, getA(), getB());
    }
}

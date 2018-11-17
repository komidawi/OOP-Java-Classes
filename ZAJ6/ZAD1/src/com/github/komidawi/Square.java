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

    @Override
    public boolean isWithinArea(Point point) {
        return point.x >= position.x && point.x <= position.x + getA()
                && point.y >= position.y && point.y <= position.y + getA();
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}

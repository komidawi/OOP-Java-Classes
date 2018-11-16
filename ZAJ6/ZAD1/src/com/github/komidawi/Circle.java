package com.github.komidawi;

import java.awt.*;

public class Circle extends Shape {

    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);

        validateLength(radius);
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval(x, y, 2*getRadius(), 2*getRadius());
    }
}

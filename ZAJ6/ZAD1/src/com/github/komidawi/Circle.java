package com.github.komidawi;

import java.awt.*;

public class Circle extends Shape {

    protected int radius;

    public Circle(Point position, int radius) {
        super(position);

        validateLength(radius);
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{radius=" + radius + "}";
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval(position.x, position.y, 2*getRadius(), 2*getRadius());
    }
}

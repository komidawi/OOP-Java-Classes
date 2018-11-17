package com.github.komidawi;

import java.awt.*;

public class Circle extends Shape {

    protected int radius;

    public Circle(Point position, int radius) {
        super(position);

        validateLength(radius);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Circle{radius=" + radius + "}";
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval(position.x, position.y, 2*getRadius(), 2*getRadius());
    }

    @Override
    public boolean isWithinArea(Point point) {
        return Math.sqrt(Math.pow(position.x + getRadius() - point.x, 2)
                + Math.pow(position.y + getRadius() - point.y, 2)) <= radius;
    }
}

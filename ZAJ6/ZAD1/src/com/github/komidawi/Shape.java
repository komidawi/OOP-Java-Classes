package com.github.komidawi;

import java.awt.*;

public abstract class Shape {
    protected String name;
    protected Point position = new Point();

    public abstract void draw(Graphics g);

    public Shape(Point position) {
        // using properties directly due to Point class architecture
        checkIfNotNull(position);
        validatePosition(position);
        this.position.x = position.x;
        this.position.y = position.y;
    }

    protected void checkIfNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    protected void validateLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }
    }

    protected void validatePosition(Point position) {
        if (position.x < 0 || position.y < 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
    }
}

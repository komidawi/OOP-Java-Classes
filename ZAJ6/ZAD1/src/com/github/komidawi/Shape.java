package com.github.komidawi;

import java.awt.*;

public abstract class Shape {
    private String name;
    protected int x;
    protected int y;

    public abstract void draw(Graphics g);

    public Shape(int x, int y) {
        validatePosition(x, y);
        this.x = x;
        this.y = y;
    }

    protected void validateLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }
    }

    protected void validatePosition(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates cannot be negative.");
        }
    }
}

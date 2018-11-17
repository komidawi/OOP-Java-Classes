package com.github.komidawi;

import java.awt.*;
import java.util.ArrayList;

public class ShapeManager {

    private ArrayList<Shape> shapes = new ArrayList<>();

    public ShapeManager() {}

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void addShape(Shape shape) {
        checkIfNotNull(shape);
        shapes.add(shape);
    }

    public void addExampleShapes() {
        addShape(new Square(new Point(5, 10), 10));
        addShape(new Rectangle(new Point(20, 20), 60, 60));
        addShape(new Circle(new Point(60, 60), 60));
    }

    private void checkIfNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }
}

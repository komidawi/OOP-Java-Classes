package com.github.komidawi;

import java.util.ArrayList;

public class ShapeManager {
    private ArrayList<Shape> shapes = new ArrayList<>();

    public ShapeManager() {}

    public ShapeManager(ShapeManager shapeManager) {
        checkIfNotNull(shapeManager);
        shapes = new ArrayList<>(shapeManager.getShapes());
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void addShape(Shape shape) {
        checkIfNotNull(shape);
        shapes.add(shape);
    }

    public void setShapes(ArrayList<Shape> shapes) {
        checkIfNotNull(shapes);
        this.shapes = new ArrayList<>(shapes);
    }

    private void checkIfNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }
}

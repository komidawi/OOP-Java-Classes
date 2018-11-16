package com.github.komidawi;

import java.awt.*;

public class MyPanel extends Panel {

    private ShapeManager shapeManager;

    public MyPanel(ShapeManager shapeManager) {
        checkIfNotNull(shapeManager);
        this.shapeManager = new ShapeManager(shapeManager);
    }

    public ShapeManager getShapeManager() {
        return shapeManager;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Shape shape : shapeManager.getShapes()) {
            shape.draw(g);
        }
    }

    private void checkIfNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }
}

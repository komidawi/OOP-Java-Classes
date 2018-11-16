package com.github.komidawi;

import javax.swing.*;
import java.awt.*;

public class ShapeDrawPanel extends JPanel {

    private ShapeManager shapeManager;

    public ShapeDrawPanel(ShapeManager shapeManager) {
        checkIfNotNull(shapeManager);
        this.shapeManager = new ShapeManager(shapeManager);
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

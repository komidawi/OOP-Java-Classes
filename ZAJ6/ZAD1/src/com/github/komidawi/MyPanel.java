package com.github.komidawi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class MyPanel extends Panel implements MouseListener, MouseMotionListener {

    private ShapeManager shapeManager;
    private int shiftX;
    private int shiftY;
    private Shape clickedShape = null;

    public enum actions{
        ADD_SQUARE,
        ADD_RECTANGLE,
        ADD_CIRCLE
    }


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

    @Override
    public void mousePressed(MouseEvent e) {
        Point clickedPoint = e.getPoint();
        clickedShape = getClickedShape(clickedPoint);

        if (clickedShape != null) {
            shiftX = clickedShape.position.x - clickedPoint.x;
            shiftY = clickedShape.position.y - clickedPoint.y;
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (clickedShape != null) {
            clickedShape.setPosition(
                    new Point(e.getPoint().x + shiftX,
                            e.getPoint().y + shiftY));
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ArrayList<Shape> shapes = shapeManager.getShapes();
        shapes.add(clickedShape);
        shapes.remove(clickedShape);
        clickedShape = null;
    }

    private Shape getClickedShape(Point clickedPoint) {
        ArrayList<Shape> shapes = shapeManager.getShapes();
        ListIterator<Shape> iterator = shapes.listIterator(shapes.size());
        while (iterator.hasPrevious()) {
            Shape current = iterator.previous();

            if (current.isWithinArea(clickedPoint)) {
                return current;
            }
        }
        return null;
    }

    private void checkIfNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

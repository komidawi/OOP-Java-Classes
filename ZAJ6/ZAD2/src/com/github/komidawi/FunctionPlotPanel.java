package com.github.komidawi;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

class FunctionPlotPanel extends JPanel {

    private static final int PREFERRED_WIDTH = 800;
    private static final int PREFERRED_HEIGHT = 600;
    private static final int BORDER_GAP = 50;
    private static final int HATCH_COUNT = 10;
    private static final int HATCH_LENGTH = 12;
    private static final int DOMAIN_TEXT_SHIFT_X = 15;
    private static final int DOMAIN_TEXT_SHIFT_Y = 20;
    private static final int CODOMAIN_TEXT_SHIFT_X = 45;
    private static final int CODOMAIN_TEXT_SHIFT_Y = 5;

    private double xScale;
    private double yScale;

    private MathPlotProperties properties;
    private ArrayList<Point2D> points;

    public FunctionPlotPanel(MathPlotProperties properties) {
        this.properties = properties;
        this.points = properties.getPointList();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        determineScale();

        drawAxes(g2d);

        drawHatchMarksForAxisX(g2d);
        drawHatchMarksForAxisY(g2d);

        drawDomainValues(g2d);
        drawCodomainValues(g2d);

        drawPlot(g2d);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
    }

    private double getMinimumRange() {
        return properties.getRange().getStart();
    }

    private double getMaximumRange() {
        return properties.getRange().getEnd();
    }

    private void determineScale() {
        xScale = ((getWidth() - 2 * BORDER_GAP) / (getMaximumRange() - getMinimumRange()));
        yScale = ((getHeight() - 2 * BORDER_GAP) / (getMaximumValue() - getMinimumValue()));
    }

    private void drawAxes(Graphics2D g2d) {
        g2d.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2d.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);
    }

    private double getMinimumValue() {
        if (points != null && points.size() > 0) {
            double minimumValue = points.get(0).getY();
            for (int i = 1; i < points.size(); i++) {
                double currentValue = points.get(i).getY();
                if (currentValue < minimumValue) {
                    minimumValue = currentValue;
                }
            }
            return minimumValue;
        } else {
            throw new RuntimeException();
        }
    }

    private double getMaximumValue() {
        if (points != null && points.size() > 0) {
            double maximumValue = points.get(0).getY();
            for (int i = 1; i < points.size(); i++) {
                double currentValue = points.get(i).getY();
                if (currentValue > maximumValue) {
                    maximumValue = currentValue;
                }
            }
            return maximumValue;
        } else {
            throw new RuntimeException();
        }
    }

    private void drawHatchMarksForAxisX(Graphics2D g2d) {
        for (int i = 0; i < HATCH_COUNT; i++) {
            int x1 = getWidth() - BORDER_GAP - i * (getWidth() - 2 * BORDER_GAP) / HATCH_COUNT;
            int y1 = getHeight() - BORDER_GAP + HATCH_LENGTH / 2;
            int x2 = x1;
            int y2 = y1 - HATCH_LENGTH;
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    private void drawHatchMarksForAxisY(Graphics2D g2d) {
        for (int i = 0; i < HATCH_COUNT; i++) {
            int x1 = BORDER_GAP - HATCH_LENGTH / 2;
            int y1 = getHeight() - BORDER_GAP - (i + 1) * (getHeight() - 2 * BORDER_GAP) / HATCH_COUNT;
            int x2 = x1 + HATCH_LENGTH;
            int y2 = y1;
            g2d.drawLine(x1, y1, x2, y2);
        }
    }

    private void drawDomainValues(Graphics2D g2d) {
        for (int i = 0; i < HATCH_COUNT+1; i++) {
            int x1 = BORDER_GAP + i * (getWidth() - 2 * BORDER_GAP) / HATCH_COUNT - DOMAIN_TEXT_SHIFT_X;
            int y1 = getHeight() - BORDER_GAP + DOMAIN_TEXT_SHIFT_Y;
            int x2 = x1;
            int y2 = y1 - HATCH_LENGTH;

            double value = getMinimumRange() + (((double) i / HATCH_COUNT) * (getMaximumRange() - getMinimumRange()));
            String string = String.format("%.2f", value);
            g2d.drawString(string, x1, y1);
        }
    }

    private void drawCodomainValues(Graphics2D g2d) {
        for (int i = 0; i < HATCH_COUNT+1; i++) {
            int x1 = BORDER_GAP - CODOMAIN_TEXT_SHIFT_X;
            int y1 = getHeight() - BORDER_GAP - i * (getHeight() - 2 * BORDER_GAP) / HATCH_COUNT + CODOMAIN_TEXT_SHIFT_Y;
            int x2 = x1;
            int y2 = y1 - HATCH_LENGTH;

            double value = getMinimumValue() + (((double) i / HATCH_COUNT) * (getMaximumValue() - getMinimumValue()));
            String string = String.format("%.2f", value);
            g2d.drawString(string, x1, y1);
        }
    }

    private void drawPlot(Graphics2D g2d) {
        for (int i = 0; i < points.size()-1; i++) {
            Point2D current = points.get(i);
            Point2D next = points.get(i+1);

            int x1 = (int) (BORDER_GAP + ((current.getX() - getMinimumRange()) * xScale));
            int y1 = (int) (getHeight() - BORDER_GAP - ((current.getY() - getMinimumValue()) * yScale));
            int x2 = (int) (BORDER_GAP + ((next.getX() - getMinimumRange()) * xScale));
            int y2 = (int) (getHeight() - BORDER_GAP - ((next.getY() - getMinimumValue()) * yScale));

            g2d.drawLine(x1, y1, x2, y2);
        }
    }
}
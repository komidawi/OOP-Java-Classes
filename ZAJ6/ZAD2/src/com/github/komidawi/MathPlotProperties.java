package com.github.komidawi;

import net.objecthunter.exp4j.Expression;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class MathPlotProperties {
    private Expression expression;
    private double[] factors;
    private Range range;
    private double frequency;


    public MathPlotProperties(double[] factors, Range range, double frequency) {
        if (factors.length > 0 && range != null && frequency != 0) {
            this.factors = factors;
            this.range = new Range(range);
            this.frequency = frequency;
        } else {
            throw new IllegalArgumentException("Wrong arguments.");
        }
    }

    public MathPlotProperties(Expression expression, Range range, double frequency) {
        if (expression != null && range != null && frequency != 0) {
            this.expression = new Expression(expression);
            this.range = new Range(range);
            this.frequency = frequency;
        } else {
            throw new IllegalArgumentException("Wrong arguments.");
        }
    }

    public Range getRange() {
        return range;
    }

    public double getValueAt(double x) {
        expression.setVariable("x", x);
        return expression.evaluate();

        /*
        double value = 0;
        for (int i = 0; i < factors.length; i++) {
            value += factors[i] * Math.pow(x, factors.length - i - 1);
        }
        return value;*/
    }

    public ArrayList<Point2D> getPointList() {
        ArrayList<Point2D> points = new ArrayList<>();
        for (double x = range.getStart(); x <= range.getEnd(); x += frequency) {
            System.out.println("x="+x+", y="+getValueAt(x));
            points.add(new Point2D.Double(x, getValueAt(x)));
        }
        return points;
    }
}

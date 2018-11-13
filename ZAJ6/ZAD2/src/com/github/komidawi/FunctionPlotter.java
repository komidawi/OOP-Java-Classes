package com.github.komidawi;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.swing.*;
import java.awt.*;


public class FunctionPlotter {

    // TODO: x and y letters on graph
    // TODO: JInput for data from user

    public void run() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        EventQueue.invokeLater(() ->
        {
            double[] factors = {4, -2, 1};
            Range range = new Range(-10.5, 10.5);
            double frequency = 3.5;

            MathPlotProperties plotProperties = askForPlotProperties();

            //MathPlotProperties plotProperties = new MathPlotProperties(factors, range, frequency);
            FunctionPlotPanel plotPanel = new FunctionPlotPanel(plotProperties);
            setupFrame(plotPanel);
        });
    }

    private void setupFrame(FunctionPlotPanel plotPanel) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(plotPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // TODO: how to automatically set preferred size without explicitly setting it?
        frame.setSize(plotPanel.getPreferredSize());
        frame.setVisible(true);
    }

    private MathPlotProperties askForPlotProperties() {
        String formula = JOptionPane.showInputDialog(
                JOptionPane.getRootFrame(),
                "Please enter function formula:  ",
                "Formula",
                JOptionPane.PLAIN_MESSAGE);

        String start = JOptionPane.showInputDialog(
                JOptionPane.getRootFrame(),
                "Please enter start point:  ",
                "Start point",
                JOptionPane.PLAIN_MESSAGE);

        String end = JOptionPane.showInputDialog(
                JOptionPane.getRootFrame(),
                "Please enter end point:  ",
                "End point",
                JOptionPane.PLAIN_MESSAGE);

        String frequency = JOptionPane.showInputDialog(
                JOptionPane.getRootFrame(),
                "Please enter frequency:  ",
                "Frequency",
                JOptionPane.PLAIN_MESSAGE);

        Expression expression = new ExpressionBuilder(formula)
                .variables("x")
                .build();

        return new MathPlotProperties(
                expression, new Range(Integer.parseInt(start), Integer.parseInt(end)),
                Integer.parseInt(frequency));
    }
}




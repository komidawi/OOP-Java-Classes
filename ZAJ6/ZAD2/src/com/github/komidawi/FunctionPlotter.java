package com.github.komidawi;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.swing.*;
import java.awt.*;


public class FunctionPlotter {

    public void run() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        EventQueue.invokeLater(() ->
        {
            MathPlotProperties plotProperties = askForPlotProperties();
            FunctionPlotPanel plotPanel = new FunctionPlotPanel(plotProperties);
            setupFrame(plotPanel);
        });
    }

    private void setupFrame(FunctionPlotPanel plotPanel) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(plotPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setSize(plotPanel.getPreferredSize());
        frame.setVisible(true);
    }

    private MathPlotProperties askForPlotProperties() {
        String formula = askForPlotProperty("formula (everything after f(x)=)\n" +
                "See https://lallafa.objecthunter.net/exp4j/index.html for more information");
        String start = askForPlotProperty("start point");
        String end = askForPlotProperty("end point");
        String frequency = askForPlotProperty("sampling frequency");

        Expression expression = new ExpressionBuilder(formula)
                .variables("x")
                .build();

        return new MathPlotProperties(
                expression,
                new Range(Integer.parseInt(start), Integer.parseInt(end)),
                Integer.parseInt(frequency));
    }

    private String askForPlotProperty(String property) {
        return JOptionPane.showInputDialog(
                JOptionPane.getRootFrame(),
                "Please enter " + property,
                "",
                JOptionPane.PLAIN_MESSAGE);
    }
}




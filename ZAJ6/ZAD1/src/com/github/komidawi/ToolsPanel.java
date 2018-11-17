package com.github.komidawi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolsPanel extends Panel {

    private AddShapeListener addShapeListener = new AddShapeListener();
    private DrawPanel drawPanel;

    public ToolsPanel(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
    }

    public void setupButtons() {
        setupAddSquareButton();
        setupAddRectangleButton();
        setupAddCircleButton();
    }

    private void setupAddSquareButton() {
        Button addSquareButton = new Button("Add square");
        addSquareButton.addActionListener(addShapeListener);
        addSquareButton.setActionCommand(DrawPanel.actions.ADD_SQUARE.name());
        add(addSquareButton);
    }

    private void setupAddRectangleButton() {
        Button addRectangleButton = new Button("Add rectangle");
        addRectangleButton.addActionListener(addShapeListener);
        addRectangleButton.setActionCommand(DrawPanel.actions.ADD_RECTANGLE.name());
        add(addRectangleButton);
    }

    private void setupAddCircleButton() {
        Button addCircleButton = new Button("Add circle");
        addCircleButton.addActionListener(addShapeListener);
        addCircleButton.setActionCommand(DrawPanel.actions.ADD_CIRCLE.name());
        add(addCircleButton);
    }

    class AddShapeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();

            try {
                Point point = askForCoordinates();

                if (actionCommand.equals(DrawPanel.actions.ADD_SQUARE.name())) {
                    String a = askForParameter("a");
                    drawPanel.getShapeManager().addShape(new Square(point, Integer.parseInt(a)));

                } else if (actionCommand.equals(DrawPanel.actions.ADD_RECTANGLE.name())) {
                    String a = askForParameter("a");
                    String b = askForParameter("b");
                    drawPanel.getShapeManager().addShape(new Rectangle(point, Integer.parseInt(a), Integer.parseInt(b)));

                } else if (actionCommand.equals(DrawPanel.actions.ADD_CIRCLE.name())) {
                    String radius = askForParameter("radius");
                    drawPanel.getShapeManager().addShape(new Circle(point, Integer.parseInt(radius)));
                }

                drawPanel.repaint();
            } catch (IllegalArgumentException exc) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                        exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private Point askForCoordinates() {
            int x = Integer.parseInt(askForParameter("x"));
            int y = Integer.parseInt(askForParameter("y"));
            return new Point(x, y);
        }

        private String askForParameter(String parameter) {
            return JOptionPane.showInputDialog(
                    JOptionPane.getRootFrame(),
                    "Enter " + parameter + ": ");
        }
    }
}

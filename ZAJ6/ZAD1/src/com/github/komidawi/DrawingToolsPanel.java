package com.github.komidawi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingToolsPanel extends Panel {

    private AddShapeButtonListener addShapeButtonListener = new AddShapeButtonListener();
    private MyPanel drawPanel;

    public DrawingToolsPanel(MyPanel drawPanel) {
        this.drawPanel = drawPanel;
    }

    public void setupButtons(MyPanel drawPanel) {
        Button addSquareButton = setupAddSquareButton();
        Button addRectangleButton = setupAddRectangleButton();
        Button addCircleButton = setupAddCircleButton();

        add(addSquareButton);
        add(addRectangleButton);
        add(addCircleButton);
    }

    private Button setupAddSquareButton() {
        Button addSquareButton = new Button("Add square");
        addSquareButton.addActionListener(addShapeButtonListener);
        addSquareButton.setActionCommand(MyPanel.actions.ADD_SQUARE.name());
        return addSquareButton;
    }

    private Button setupAddRectangleButton() {
        Button addRectangleButton = new Button("Add rectangle");
        addRectangleButton.addActionListener(addShapeButtonListener);
        addRectangleButton.setActionCommand(MyPanel.actions.ADD_RECTANGLE.name());
        return addRectangleButton;
    }

    private Button setupAddCircleButton() {
        Button addCircleButton = new Button("Add circle");
        addCircleButton.addActionListener(addShapeButtonListener);
        addCircleButton.setActionCommand(MyPanel.actions.ADD_CIRCLE.name());
        return addCircleButton;
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

    class AddShapeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();

            try {
                Point point = askForCoordinates();

                if (actionCommand.equals(MyPanel.actions.ADD_SQUARE.name())) {
                    String a = askForParameter("a");
                    drawPanel.getShapeManager().addShape(new Square(point, Integer.parseInt(a)));

                } else if (actionCommand.equals(MyPanel.actions.ADD_RECTANGLE.name())) {
                    String a = askForParameter("a");
                    String b = askForParameter("b");
                    drawPanel.getShapeManager().addShape(new Rectangle(point, Integer.parseInt(a), Integer.parseInt(b)));

                } else if (actionCommand.equals(MyPanel.actions.ADD_CIRCLE.name())) {
                    String radius = askForParameter("radius");
                    drawPanel.getShapeManager().addShape(new Circle(point, Integer.parseInt(radius)));
                }
                drawPanel.repaint();
            } catch (IllegalArgumentException exc) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                        exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

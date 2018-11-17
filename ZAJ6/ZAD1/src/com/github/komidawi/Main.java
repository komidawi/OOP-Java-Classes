package com.github.komidawi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    /* Napisz prosty program umożliwiający
    przeglądanie kolejnych elementów listy i dodawanie nowych */

    public static void main(String[] args) {
        startGUI();
    }

    private static void startGUI() {
        EventQueue.invokeLater(() ->
        {
            ShapeManager shapeManager = setupShapeManager();
            MyPanel drawPanel = new MyPanel(shapeManager);
            drawPanel.addMouseListener(drawPanel);
            drawPanel.addMouseMotionListener(drawPanel);

            Panel rootPanel = new Panel();
            rootPanel.setLayout(new BorderLayout());

            Panel controlPanel = new Panel();
            controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

            Button addSquare = new Button("Add square");
            addSquare.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String a = JOptionPane.showInputDialog(
                            JOptionPane.getRootFrame(),
                            "Enter a: "
                    );
                    String x = JOptionPane.showInputDialog(
                            JOptionPane.getRootFrame(),
                            "Enter x:"
                    );
                    String y = JOptionPane.showInputDialog(
                            JOptionPane.getRootFrame(),
                            "Enter y:"
                    );
                    drawPanel.getShapeManager().addShape(new Square(new Point(Integer.parseInt(x), Integer.parseInt(y)), Integer.parseInt(a)));
                    drawPanel.repaint();
                }
            });
            Button addRectangle = new Button("Add rectangle");
            addRectangle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String a = JOptionPane.showInputDialog(
                            JOptionPane.getRootFrame(),
                            "Enter a: "
                    );
                    String b = JOptionPane.showInputDialog(
                            JOptionPane.getRootFrame(),
                            "Enter b: "
                    );
                    String x = JOptionPane.showInputDialog(
                            JOptionPane.getRootFrame(),
                            "Enter x:"
                    );
                    String y = JOptionPane.showInputDialog(
                            JOptionPane.getRootFrame(),
                            "Enter y:"
                    );
                    drawPanel.getShapeManager().addShape(new Rectangle(new Point(Integer.parseInt(x), Integer.parseInt(y)), Integer.parseInt(a), Integer.parseInt(b)));
                    drawPanel.repaint();
                }
            });
            Button addCircle = new Button("Add circle");
            addCircle.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String radius = JOptionPane.showInputDialog(
                            JOptionPane.getRootFrame(),
                            "Enter radius: "
                    );
                    String x = JOptionPane.showInputDialog(
                            JOptionPane.getRootFrame(),
                            "Enter x:"
                    );
                    String y = JOptionPane.showInputDialog(
                            JOptionPane.getRootFrame(),
                            "Enter y:"
                    );
                    drawPanel.getShapeManager().addShape(new Circle(new Point(Integer.parseInt(x), Integer.parseInt(y)), Integer.parseInt(radius)));
                    drawPanel.repaint();
                }
            });

            controlPanel.add(addSquare);
            controlPanel.add(addRectangle);
            controlPanel.add(addCircle);

            rootPanel.add(BorderLayout.CENTER, drawPanel);
            rootPanel.add(BorderLayout.EAST, controlPanel);

            Frame frame = new Frame();
            frame.add(rootPanel);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frame.dispose();
                }
            });
            frame.setSize(640, 480);
            frame.setVisible(true);
        });
    }

    private static ShapeManager setupShapeManager() {
        ShapeManager shapeManager = new ShapeManager();
        shapeManager.addShape(new Square(new Point(5, 10), 10));
        shapeManager.addShape(new Rectangle(new Point(20, 20), 60, 60));
        shapeManager.addShape(new Circle(new Point(60, 60), 60));
        return shapeManager;
    }
}

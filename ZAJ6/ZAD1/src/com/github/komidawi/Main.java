package com.github.komidawi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        // TODO unstatic methods
        new Main().startGUI();
    }

    private void startGUI() {
        EventQueue.invokeLater(() ->
        {
            ShapeManager shapeManager = setupShapeManager();
            MyPanel drawPanel = setupDrawPanel(shapeManager);
            Panel rootPanel = setupRootPanel();
            DrawingToolsPanel controlPanel = setupDrawingToolsPanel(drawPanel);

            controlPanel.setupButtons(drawPanel);

            rootPanel.add(BorderLayout.CENTER, drawPanel);
            rootPanel.add(BorderLayout.EAST, controlPanel);

            setupFrame(rootPanel);
        });
    }

    private ShapeManager setupShapeManager() {
        ShapeManager shapeManager = new ShapeManager();
        shapeManager.addShape(new Square(new Point(5, 10), 10));
        shapeManager.addShape(new Rectangle(new Point(20, 20), 60, 60));
        shapeManager.addShape(new Circle(new Point(60, 60), 60));
        return shapeManager;
    }

    private MyPanel setupDrawPanel(ShapeManager shapeManager) {
        MyPanel drawPanel = new MyPanel(shapeManager);
        drawPanel.addMouseListener(drawPanel);
        drawPanel.addMouseMotionListener(drawPanel);
        return drawPanel;
    }

    private Panel setupRootPanel() {
        Panel rootPanel = new Panel();
        rootPanel.setLayout(new BorderLayout());
        return rootPanel;
    }

    private DrawingToolsPanel setupDrawingToolsPanel(MyPanel drawPanel) {
        DrawingToolsPanel panel = new DrawingToolsPanel(drawPanel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private void setupFrame(Panel rootPanel) {
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
    }
}
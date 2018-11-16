package com.github.komidawi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        setupShapeManager();
        setupGui();

        // write your code here
    }

    private static void setupGui() {
        EventQueue.invokeLater(() ->
        {
            JFrame frame = new JFrame();

            ShapeManager shapeManager = setupShapeManager();
            ShapeDrawPanel drawPanel = new ShapeDrawPanel(shapeManager);
            frame.getContentPane().add(drawPanel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(640, 480);
            frame.setVisible(true);
        });
    }

    private static ShapeManager setupShapeManager() {
        ShapeManager shapeManager = new ShapeManager();
        shapeManager.addShape(new Square(5, 10, 10));
        shapeManager.addShape(new Rectangle(20, 20, 60, 60));
        shapeManager.addShape(new Circle(60, 60, 60));
        return shapeManager;
    }
}

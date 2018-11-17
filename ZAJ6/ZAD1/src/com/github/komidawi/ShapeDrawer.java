package com.github.komidawi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShapeDrawer {

    public void start() {
        startGUI();
    }

    private void startGUI() {
        EventQueue.invokeLater(() ->
        {
            ShapeManager shapeManager = setupShapeManager();
            DrawPanel drawPanel = new DrawPanel(shapeManager);
            ToolsPanel toolsPanel = setupToolsPanel(drawPanel);
            Panel rootPanel = setupRootPanel(drawPanel, toolsPanel);
            setupFrame(rootPanel);
        });
    }

    private ShapeManager setupShapeManager() {
        ShapeManager shapeManager = new ShapeManager();
        shapeManager.addExampleShapes();
        return shapeManager;
    }

    private ToolsPanel setupToolsPanel(DrawPanel drawPanel) {
        ToolsPanel toolsPanel = new ToolsPanel(drawPanel);
        toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.Y_AXIS));
        toolsPanel.setupButtons();
        return toolsPanel;
    }

    private Panel setupRootPanel(DrawPanel drawPanel, ToolsPanel toolsPanel) {
        Panel rootPanel = new Panel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(BorderLayout.CENTER, drawPanel);
        rootPanel.add(BorderLayout.EAST, toolsPanel);
        return rootPanel;
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

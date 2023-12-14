package com.processinformationsystemsui.common;

import com.processinformationsystemsui.common.button.EditButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Common {
    public static void initializeLabelsPanel(Dimensions dimensions, List<JLabel> labels, JPanel mainPanel, String title, Runnable action) {
        // Panel for labels information
        JPanel labelsPanel = new JPanel(new GridLayout(dimensions.x(), dimensions.y()));

        Common.addLabelsToPanel(labelsPanel, labels);

        JButton editButton = new EditButton(action);

        // Create a panel for the "EDIT" button with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(editButton);

        // Create the main panel with a BoxLayout along the Y-axis
        mainPanel.setBorder(BorderFactory.createTitledBorder(title));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Add the labels panel and button panel to the main panel
        mainPanel.add(labelsPanel);
        mainPanel.add(buttonPanel);
    }

    public static void initializeTextAreaPanel(JTextArea textArea, JPanel mainPanel, String title, Runnable action) {
        // Panel for labels information
        textArea.setEditable(false);
        textArea.setLineWrap(true); // Enable word wrap
        textArea.setWrapStyleWord(true); // Wrap at word boundaries
        textArea.setOpaque(false);

        mainPanel.add(textArea);

        JButton editButton = new EditButton(action);

        // Create a panel for the "EDIT" button with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(editButton);

        // Create the main panel with a BoxLayout along the Y-axis
        mainPanel.setBorder(BorderFactory.createTitledBorder(title));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Add the labels panel and button panel to the main panel
        mainPanel.add(buttonPanel);
    }

    public static void addMouseListener(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(Cursor.getDefaultCursor());
            }
        });
    }
    public static void addLabelsToPanel(Container container, List<JLabel> labels) {
        for(JLabel label: labels) {
            container.add(label);
        }
    }

    public static void addLabelsToPanelWithFont(Container container, List<JLabel> labels, Font font) {
        for(JLabel label: labels) {
            label.setFont(font);
            container.add(label);
        }
    }


    public static void setFontForAllComponents(Container container, Font font) {
        Component[] components = container.getComponents();

        for(Component component: components) {
            if (component instanceof JComponent) {
                ((JComponent) component).setFont(font);
            }
            if (component instanceof Container) {
                setFontForAllComponents((Container) component, font);
            }
        }
    }

    public static void setFontForAllLabels(JLabel[] labels, Font font) {
        for(JLabel label: labels) {
            label.setFont(font);
        }
    }
}

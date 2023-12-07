package com.processinformationsystemsui.panel.Epizoda;

import com.processinformationsystemsui.model.EpizodaModel;

import javax.swing.*;

public class Epizoda extends JFrame {
    public Epizoda (EpizodaModel epizoda) {
        // Create an instance of Epizoda panel
        EpizodaPanel epizodaPanel = new EpizodaPanel(epizoda);

        // Set the Epizoda panel as the content pane
        setContentPane(epizodaPanel);

        setTitle(epizoda.getNazivEpizode());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

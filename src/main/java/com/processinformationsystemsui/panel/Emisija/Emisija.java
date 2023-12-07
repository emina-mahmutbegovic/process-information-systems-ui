package com.processinformationsystemsui.panel.Emisija;

import com.processinformationsystemsui.model.EmisijaModel;

import javax.swing.*;
import java.io.IOException;

public class Emisija extends JFrame {
    public Emisija (EmisijaModel emisija) throws IOException {
        // Create an instance of Emisija panel
        EmisijaPanel emisijaPanel = new EmisijaPanel(emisija);

        // Set the Emisija panel as the content pane
        setContentPane(emisijaPanel);

        setTitle(emisija.getNazivEmisije());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

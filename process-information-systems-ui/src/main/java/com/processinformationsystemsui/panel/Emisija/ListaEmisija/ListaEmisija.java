package com.processinformationsystemsui.panel.Emisija.ListaEmisija;

import com.processinformationsystemsui.panel.Epizoda.ListaEpizoda.ListaEpizodaPanel;

import javax.swing.*;
import java.io.IOException;

public class ListaEmisija extends JFrame {
    public ListaEmisija() throws IOException {
        // Create an instance of ListaEmisija panel
        ListaEmisijaPanel listaEmisijaPanel = new ListaEmisijaPanel();

        // Set the ListaEmisija panel as the content pane
        setContentPane(listaEmisijaPanel);

        setTitle("Emisije");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

package com.processinformationsystemsui.panel.Epizoda.ListaEpizoda;

import javax.swing.*;
import java.io.IOException;

public class ListaEpizoda extends JFrame {
    public ListaEpizoda(String nazivEmisije, String idEmisije) throws IOException {
        // Create an instance of ListaEmisija panel
        ListaEpizodaPanel listaEpizodaPanel = new ListaEpizodaPanel(idEmisije);

        // Set the ListaEpizodaPanel panel as the content pane
        setContentPane(listaEpizodaPanel);

        setTitle(String.format("Lista epizoda za emisiju: %s", nazivEmisije));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

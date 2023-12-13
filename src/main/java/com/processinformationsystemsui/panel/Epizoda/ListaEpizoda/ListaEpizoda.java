package com.processinformationsystemsui.panel.Epizoda.ListaEpizoda;

import com.processinformationsystemsui.common.EmisijaDataChangeListener;
import com.processinformationsystemsui.common.frame.BaseFrame;

import java.io.IOException;

public class ListaEpizoda extends BaseFrame {
    public ListaEpizoda(String title, String idEmisije, EmisijaDataChangeListener listener) throws IOException {
        super(title);

        // Create an instance of ListaEmisija panel
        ListaEpizodaPanel listaEpizodaPanel = new ListaEpizodaPanel(idEmisije, listener, this);

        // Set the ListaEpizodaPanel panel as the content pane
        setContentPane(listaEpizodaPanel);
    }
}

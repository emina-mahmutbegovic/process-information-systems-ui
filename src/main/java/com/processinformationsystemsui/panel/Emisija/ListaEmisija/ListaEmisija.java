package com.processinformationsystemsui.panel.Emisija.ListaEmisija;

import com.processinformationsystemsui.common.frame.BaseFrame;

import java.io.IOException;

public class ListaEmisija extends BaseFrame {
    public ListaEmisija() throws IOException {
        super("Emisije");

        // Create an instance of ListaEmisija panel
        ListaEmisijaPanel listaEmisijaPanel = new ListaEmisijaPanel();

        // Set the ListaEmisija panel as the content pane
        setContentPane(listaEmisijaPanel);
    }
}

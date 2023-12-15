package com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.Edit;

import com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.ListaVoditeljaBaseFrame;

import java.io.IOException;

public class ListaVoditeljaEdit extends ListaVoditeljaBaseFrame {
    public ListaVoditeljaEdit() throws IOException {
        super();

        // Create an instance of ListaVoditelja panel
        ListaVoditeljaPanelEdit listaVoditeljaPanel = new ListaVoditeljaPanelEdit();

        // Set the ListaVoditelja panel as the content pane
        setContentPane(listaVoditeljaPanel);
    }
}

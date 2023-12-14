package com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.Edit;

import com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.ListaVrstaEmisijaBaseFrame;

import java.io.IOException;

public class ListaVrstaEmisijaEdit extends ListaVrstaEmisijaBaseFrame {
    public ListaVrstaEmisijaEdit() throws IOException {
        super();

        // Create an instance of ListaVrstaEmisija panel
        ListaVrstaEmisijaPanelEdit listaVrstaEmisijaPanel = new ListaVrstaEmisijaPanelEdit();

        // Set the ListaVrstaEmisija panel as the content pane
        setContentPane(listaVrstaEmisijaPanel);
    }
}

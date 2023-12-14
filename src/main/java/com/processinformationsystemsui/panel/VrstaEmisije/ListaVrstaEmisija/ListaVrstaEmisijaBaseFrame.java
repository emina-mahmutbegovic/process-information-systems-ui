package com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija;

import com.processinformationsystemsui.common.frame.BaseFrame;
import com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.Edit.ListaVrstaEmisijaPanelEdit;

import java.io.IOException;

public class ListaVrstaEmisijaBaseFrame extends BaseFrame {
    public ListaVrstaEmisijaBaseFrame() throws IOException {
        super("Vrste emisija");

        // Create an instance of ListaVrstaEmisija panel
        ListaVrstaEmisijaPanelEdit listaVrstaEmisijaPanel = new ListaVrstaEmisijaPanelEdit();

        // Set the ListaVrstaEmisija panel as the content pane
        setContentPane(listaVrstaEmisijaPanel);
    }
}

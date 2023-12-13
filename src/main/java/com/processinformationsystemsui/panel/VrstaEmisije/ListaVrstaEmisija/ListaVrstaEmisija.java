package com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija;

import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.common.frame.BaseFrame;

import java.io.IOException;

public class ListaVrstaEmisija extends BaseFrame {
    public ListaVrstaEmisija(EmisijaDataChangeListener listener, Boolean isFromEmisijaPanel) throws IOException {
        super("Vrste emisija");

        // Create an instance of ListaVrstaEmisija panel
        ListaVrstaEmisijaPanel listaVrstaEmisijaPanel = new ListaVrstaEmisijaPanel(listener, isFromEmisijaPanel);

        // Set the ListaVrstaEmisija panel as the content pane
        setContentPane(listaVrstaEmisijaPanel);
    }
}

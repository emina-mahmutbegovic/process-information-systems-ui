package com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.Select;

import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.ListaVrstaEmisijaBaseFrame;

import java.io.IOException;

public class ListaVrstaEmisijaSelect extends ListaVrstaEmisijaBaseFrame {
    public ListaVrstaEmisijaSelect(EmisijaDataChangeListener listener) throws IOException {
        super();

        // Create an instance of ListaVrstaEmisija panel
        ListaVrstaEmisijaPanelSelect listaVrstaEmisijaPanel = new ListaVrstaEmisijaPanelSelect(listener);

        // Set the ListaVrstaEmisija panel as the content pane
        setContentPane(listaVrstaEmisijaPanel);
    }
}

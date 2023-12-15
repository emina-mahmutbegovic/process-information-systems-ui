package com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.Select;

import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.ListaVoditeljaBaseFrame;

import java.io.IOException;

public class ListaVoditeljaSelect extends ListaVoditeljaBaseFrame {
    public ListaVoditeljaSelect(EmisijaDataChangeListener listener) throws IOException {
        super();

        // Create an instance of ListaVoditelja panel
        ListaVoditeljaPanelSelect listaVoditeljaPanel = new ListaVoditeljaPanelSelect(listener);

        // Set the ListaVoditelja panel as the content pane
        setContentPane(listaVoditeljaPanel);
    }
}

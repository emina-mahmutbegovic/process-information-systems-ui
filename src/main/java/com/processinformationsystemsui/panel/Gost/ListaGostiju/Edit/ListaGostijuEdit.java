package com.processinformationsystemsui.panel.Gost.ListaGostiju.Edit;

import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Gost.ListaGostiju.ListaGostijuBaseFrame;

import java.io.IOException;

public class ListaGostijuEdit extends ListaGostijuBaseFrame {
    public ListaGostijuEdit() throws IOException {
        super();

        // Create an instance of ListaGostiju panel
        ListaGostijuPanelEdit listaGostijuPanelEdit = new ListaGostijuPanelEdit();

        // Set the ListaVoditelja panel as the content pane
        setContentPane(listaGostijuPanelEdit);
    }
}

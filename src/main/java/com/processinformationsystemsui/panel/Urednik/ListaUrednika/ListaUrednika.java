package com.processinformationsystemsui.panel.Urednik.ListaUrednika;

import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.common.frame.BaseFrame;

import java.io.IOException;

public class ListaUrednika extends BaseFrame {
    public ListaUrednika(EmisijaDataChangeListener listener, Boolean isFromEmisijaPanel) throws IOException {
        super("Urednici");

        // Create an instance of ListaUrednika panel
        ListaUrednikaPanel listaUrednikaPanel = new ListaUrednikaPanel(listener, isFromEmisijaPanel);

        // Set the ListaUrednika panel as the content pane
        setContentPane(listaUrednikaPanel);
    }
}

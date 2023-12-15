package com.processinformationsystemsui.panel.Urednik.ListaUrednika.Select;

import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Urednik.ListaUrednika.ListaUrednikaBaseFrame;

import java.io.IOException;

public class ListaUrednikaSelect extends ListaUrednikaBaseFrame {
    public ListaUrednikaSelect(EmisijaDataChangeListener listener) throws IOException {
        super();

        // Create an instance of ListaUrednika panel
        ListaUrednikaPanelSelect listaUrednikaPanel = new ListaUrednikaPanelSelect(listener);

        // Set the ListaVoditelja panel as the content pane
        setContentPane(listaUrednikaPanel);
    }
}

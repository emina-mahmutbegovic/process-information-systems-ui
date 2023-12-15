package com.processinformationsystemsui.panel.Urednik.ListaUrednika.Edit;

import com.processinformationsystemsui.panel.Urednik.ListaUrednika.ListaUrednikaBaseFrame;

import java.io.IOException;

public class ListaUrednikaEdit extends ListaUrednikaBaseFrame {
    public ListaUrednikaEdit() throws IOException {
        super();

        // Create an instance of ListaUrednika panel
        ListaUrednikaPanelEdit listaUrednikaPanelEdit = new ListaUrednikaPanelEdit();

        // Set the ListaGostiju panel as the content pane
        setContentPane(listaUrednikaPanelEdit);
    }
}

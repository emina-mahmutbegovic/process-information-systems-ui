package com.processinformationsystemsui.panel.Gost.ListaGostiju;

import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.common.frame.BaseFrame;

import java.io.IOException;

public class ListaGostiju extends BaseFrame {
    public ListaGostiju(EmisijaDataChangeListener listener) throws IOException {
        super("Gosti");

        // Create an instance of ListaGostiju panel
        ListaGostijuPanel listaGostijuPanel = new ListaGostijuPanel("", listener, this);

        // Set the ListaGostiju panel as the content pane
        setContentPane(listaGostijuPanel);
    }
}

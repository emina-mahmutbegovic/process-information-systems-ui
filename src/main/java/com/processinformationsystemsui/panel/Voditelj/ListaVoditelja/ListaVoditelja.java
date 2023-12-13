package com.processinformationsystemsui.panel.Voditelj.ListaVoditelja;

import com.processinformationsystemsui.common.EmisijaDataChangeListener;
import com.processinformationsystemsui.common.frame.BaseFrame;

import java.io.IOException;

public class ListaVoditelja extends BaseFrame {
    public ListaVoditelja(EmisijaDataChangeListener listener, Boolean isFromEmisijaPanel) throws IOException {
        super("Voditelji");

        // Create an instance of ListaVoditelja panel
        ListaVoditeljaPanel listaVoditeljaPanel = new ListaVoditeljaPanel(listener, isFromEmisijaPanel);

        // Set the ListaVoditelja panel as the content pane
        setContentPane(listaVoditeljaPanel);
    }
}

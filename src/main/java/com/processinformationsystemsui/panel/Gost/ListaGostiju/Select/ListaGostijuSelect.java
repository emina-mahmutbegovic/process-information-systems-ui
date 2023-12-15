package com.processinformationsystemsui.panel.Gost.ListaGostiju.Select;

import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Gost.ListaGostiju.ListaGostijuBaseFrame;
import com.processinformationsystemsui.panel.Gost.ListaGostiju.Select.ListaGostijuPanelSelect;

import javax.swing.*;
import java.io.IOException;

public class ListaGostijuSelect extends ListaGostijuBaseFrame {
    public ListaGostijuSelect(String idEmisije, EmisijaDataChangeListener listener, JFrame parentFrame) throws IOException {
        super();

        // Create an instance of ListaGostiju panel
        ListaGostijuPanelSelect listaGostijuPanelSelect = new ListaGostijuPanelSelect(idEmisije, listener, parentFrame);

        // Set the ListaGostiju panel as the content pane
        setContentPane(listaGostijuPanelSelect);
    }
}

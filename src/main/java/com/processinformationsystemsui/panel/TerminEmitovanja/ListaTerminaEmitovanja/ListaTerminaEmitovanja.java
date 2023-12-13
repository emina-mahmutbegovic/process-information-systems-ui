package com.processinformationsystemsui.panel.TerminEmitovanja.ListaTerminaEmitovanja;

import com.processinformationsystemsui.common.EpizodaDataChangeListener;
import com.processinformationsystemsui.common.frame.BaseFrame;
import com.processinformationsystemsui.model.EpizodaModel;

import java.io.IOException;

public class ListaTerminaEmitovanja extends BaseFrame {
    public ListaTerminaEmitovanja(String title, EpizodaModel epizoda, EpizodaDataChangeListener listener) throws IOException {
        super(title);

        // Create an instance of ListaTerminaEmitovanja panel
        ListaTerminaEmitovanjaPanel listaTerminaEmitovanjaPanel = new ListaTerminaEmitovanjaPanel(epizoda, listener, this);

        // Set the ListaTerminaEmitovanja panel as the content pane
        setContentPane(listaTerminaEmitovanjaPanel);
    }
}

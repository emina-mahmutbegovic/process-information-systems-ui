package com.processinformationsystemsui.panel.Emisija;

import com.processinformationsystemsui.common.frame.BaseFrame;
import com.processinformationsystemsui.model.EmisijaModel;

import java.io.IOException;

public class Emisija extends BaseFrame {
    public Emisija (EmisijaModel emisija) throws IOException {
        super(emisija.getNazivEmisije());

        // Create an instance of Emisija panel
        EmisijaPanel emisijaPanel = new EmisijaPanel(emisija, this);

        // Set the Emisija panel as the content pane
        setContentPane(emisijaPanel);
    }
}

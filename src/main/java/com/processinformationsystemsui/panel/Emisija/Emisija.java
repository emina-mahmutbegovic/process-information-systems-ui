package com.processinformationsystemsui.panel.Emisija;

import com.processinformationsystemsui.common.frame.BaseFrame;

import java.io.IOException;

public class Emisija extends BaseFrame {
    public Emisija (String title, EmisijaPanel emisijaPanel) throws IOException {
        super(title);

        // Set the Emisija panel as the content pane
        setContentPane(emisijaPanel);
    }
}

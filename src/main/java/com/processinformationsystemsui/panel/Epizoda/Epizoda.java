package com.processinformationsystemsui.panel.Epizoda;

import com.processinformationsystemsui.common.frame.BaseFrame;

public class Epizoda extends BaseFrame {
    public Epizoda (String title, EpizodaPanel epizodaPanel) {
        super(title);

        // Set the Epizoda panel as the content pane
        setContentPane(epizodaPanel);
    }
}

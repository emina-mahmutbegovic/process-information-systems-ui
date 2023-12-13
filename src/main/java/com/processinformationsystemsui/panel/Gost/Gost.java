package com.processinformationsystemsui.panel.Gost;

import com.processinformationsystemsui.common.frame.BaseFrame;

import javax.swing.*;

public class Gost extends BaseFrame {
    public Gost(String title, GostPanelBase gostPanelBase) {
        super(title);

        // Set the Gost panel as the content pane
        setContentPane(gostPanelBase);
    }
}

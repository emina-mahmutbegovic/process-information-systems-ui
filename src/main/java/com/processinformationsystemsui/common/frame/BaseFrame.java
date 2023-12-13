package com.processinformationsystemsui.common.frame;

import javax.swing.*;

public class BaseFrame extends JFrame {
    public BaseFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation();
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void setDefaultCloseOperation() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}

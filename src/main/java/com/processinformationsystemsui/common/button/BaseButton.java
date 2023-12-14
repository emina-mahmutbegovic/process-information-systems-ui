package com.processinformationsystemsui.common.button;

import com.processinformationsystemsui.common.Common;

import javax.swing.*;

public class BaseButton extends JButton {
    public BaseButton(Runnable action) {
        Common.addMouseListener(this);

        this.addActionListener(e -> {
            action.run();
        });
    }
}

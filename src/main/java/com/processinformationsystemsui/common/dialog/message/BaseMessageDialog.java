package com.processinformationsystemsui.common.dialog.message;

import javax.swing.*;
import java.awt.*;

public class BaseMessageDialog {
    private final Component component;
    private final String title;
    private final int messageType;

    public BaseMessageDialog(Component component, String title, int messageType) {
        this.component = component;
        this.title = title;
        this.messageType = messageType;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(component, message, title, messageType);
    }
}

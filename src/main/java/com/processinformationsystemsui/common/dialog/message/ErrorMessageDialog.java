package com.processinformationsystemsui.common.dialog.message;

import javax.swing.*;
import java.awt.*;

public class ErrorMessageDialog extends BaseMessageDialog {
    public ErrorMessageDialog(Component component) {
        super(component, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

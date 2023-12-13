package com.processinformationsystemsui.common.dialog.message;

import javax.swing.*;
import java.awt.*;

public class ValidationErrorMessageDialog extends BaseMessageDialog {
    public ValidationErrorMessageDialog(Component component) {
        super(component,"Validation Error", JOptionPane.ERROR_MESSAGE);
    }
}

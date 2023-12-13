package com.processinformationsystemsui.common.dialog.message;

import javax.swing.*;
import java.awt.*;

public class InformationMessageDialog extends BaseMessageDialog {
    public InformationMessageDialog(Component component) {
        super(component,"Success", JOptionPane.INFORMATION_MESSAGE);
    }
}

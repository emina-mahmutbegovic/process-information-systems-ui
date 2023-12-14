package com.processinformationsystemsui.common.dialog.delete;

import com.processinformationsystemsui.common.button.DeleteButton;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;

import javax.swing.*;
import java.awt.*;

public class BaseDeleteElementDialog extends JDialog {
    protected BaseDeleteElementDialog(JFrame parentFrame, String title) {
        super(parentFrame, title, true);

        InformationMessageDialog informationMessageDialog = new InformationMessageDialog(this);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());

        Runnable deleteAction = () -> {
            // Create and show the delete confirmation dialog
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete?",
                    "Delete Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                // Perform the delete operation here
                emitItemDeletion();
                informationMessageDialog.showMessage("Item successfully deleted!");
                dispose();
            }
        };

        JButton deleteButton = new DeleteButton(deleteAction);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        panel.add(deleteButton, constraints);

        add(panel);

        pack();
        setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
        setSize(300, 200);
        setResizable(false);
    }

    protected void emitItemDeletion() {
        // Implement in child classes
    }
}

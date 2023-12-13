package com.processinformationsystemsui.common.dialog.delete;

import com.processinformationsystemsui.common.Common;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;

import javax.swing.*;
import java.awt.*;

public class BaseDeleteElementDialog extends JDialog {
    protected BaseDeleteElementDialog(JFrame parentFrame, String title) {
        super(parentFrame, title, true);

        InformationMessageDialog informationMessageDialog = new InformationMessageDialog(this);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = new JButton("Delete");
        deleteButton.setForeground(Color.red);

        panel.add(deleteButton);
        add(panel);

        Common.addMouseListener(deleteButton);

        deleteButton.addActionListener(e -> {
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
        });

        pack();
        setLocationRelativeTo(null);
        setSize(300, 200);
        setResizable(false);
    }

    protected void emitItemDeletion() {
        // Implement in child classes
    }
}

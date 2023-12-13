package com.processinformationsystemsui.common.dialog.create;

import com.processinformationsystemsui.common.dialog.message.ValidationErrorMessageDialog;
import com.processinformationsystemsui.panel.TerminEmitovanja.ListaTerminaEmitovanja.ListaTerminaEmitovanja;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseCreateNewElementDialog extends JDialog {
    protected final List<JLabel> labels;
    protected final List<JTextField> textFieldList;
    public BaseCreateNewElementDialog(JFrame parentFrame, String title, int rows, int cols) {
        super(parentFrame, title, true);

        ValidationErrorMessageDialog validationErrorMessageDialog = new ValidationErrorMessageDialog(SwingUtilities.getWindowAncestor(this));

        // Create a panel to hold the input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(rows, cols));
        inputPanel.setBorder(BorderFactory.createTitledBorder(""));

        labels = setLabels();
        textFieldList = setTextFields();

        if(labels.size() != textFieldList.size()) {
            throw new ArrayIndexOutOfBoundsException("Labels length different than text fields length!");
        }

        // Initialize input panel
        for(int i = 0; i < labels.size(); ++i) {
            inputPanel.add(labels.get(i));
            inputPanel.add(textFieldList.get(i));
        }

        // Create OK and Cancel buttons
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            // Validate and set inputs
            // Check if all fields are filled
            boolean isValidInput = true;
            List<String> output = new ArrayList<>();
            for(JTextField textField : textFieldList) {
                if(textField.getText().isEmpty()) {
                    isValidInput = false;
                } else {
                    output.add(textField.getText());
                }
            }

            if (Boolean.FALSE.equals(isValidInput)) {
                validationErrorMessageDialog.showMessage("All fields must be filled.");
            } else {
                // Process the input
                try {
                    processData(output);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                // Clear lists
                textFieldList.clear();
                labels.clear();
                // Close the dialog
                dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            // Close the dialog without processing
            dispose();
        });

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add input panel and button panel to the dialog
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parentFrame);
        setSize(400, 300);
        setResizable(false);
    }

    protected List<JLabel> setLabels() {
        // Implement in child classes
        return Collections.emptyList();
    }

    protected List<JTextField> setTextFields() {
        // Implement in child classes
        return Collections.emptyList();
    }

    // Method to send data to the parent panel
    protected void processData(List<String> data) throws ParseException {
        // Implement in child classes
    }
}

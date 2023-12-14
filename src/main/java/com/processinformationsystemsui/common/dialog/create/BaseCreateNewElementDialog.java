package com.processinformationsystemsui.common.dialog.create;

import com.processinformationsystemsui.common.Dimensions;
import com.processinformationsystemsui.common.button.CancelButton;
import com.processinformationsystemsui.common.button.OkButton;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;
import com.processinformationsystemsui.common.dialog.message.ValidationErrorMessageDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseCreateNewElementDialog extends JDialog {
    protected final List<JLabel> labels;
    protected final List<JTextField> textFieldList;
    public BaseCreateNewElementDialog(JFrame parentFrame, String title, Dimensions dimensions) {
        super(parentFrame, title, true);

        ValidationErrorMessageDialog validationErrorMessageDialog = new ValidationErrorMessageDialog(BaseCreateNewElementDialog.this);
        InformationMessageDialog informationMessageDialog = new InformationMessageDialog(BaseCreateNewElementDialog.this);

        // Create a panel to hold the input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(dimensions.x(), dimensions.y()));
        inputPanel.setBorder(BorderFactory.createTitledBorder(""));

        labels = setLabels();
        textFieldList = setTextFields();

        if(labels.size() != textFieldList.size()) {
            throw new ArrayIndexOutOfBoundsException("Labels length different than text fields length!");
        }

        // Initialize input panel
        for(int i = 0; i < labels.size(); ++i) {
            JPanel labelPanel = new JPanel(new FlowLayout());
            labelPanel.add(labels.get(i));
            inputPanel.add(labelPanel);

            JPanel textFieldPanel = new JPanel(new FlowLayout());
            textFieldPanel.add(textFieldList.get(i));
            inputPanel.add(textFieldPanel);
        }

        // Create OK and Cancel buttons
        Runnable okAction = () -> {
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
                    informationMessageDialog.showMessage("Item successfully created!");
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }

                // Clear lists
                textFieldList.clear();
                labels.clear();
                // Close the dialog
                dispose();
            }
        };

        JButton okButton = new OkButton(okAction);

        JButton cancelButton = new CancelButton(this::dispose);

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
    protected void processData(List<String> data) throws ParseException, IOException {
        // Implement in child classes
    }
}

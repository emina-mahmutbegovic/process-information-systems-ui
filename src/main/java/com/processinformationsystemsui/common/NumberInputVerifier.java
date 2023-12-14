package com.processinformationsystemsui.common;

import com.processinformationsystemsui.common.dialog.create.BaseCreateNewElementDialog;
import com.processinformationsystemsui.common.dialog.message.ValidationErrorMessageDialog;

import javax.swing.*;

public class NumberInputVerifier extends InputVerifier {
    private final BaseCreateNewElementDialog parentFrame;
    public NumberInputVerifier(BaseCreateNewElementDialog parentFrame) {
        this.parentFrame = parentFrame;
    }
    @Override
    public boolean verify(JComponent input) {
        JTextField textField = (JTextField) input;
        String text = textField.getText();

        // Check if the input is a valid number
        try {
            Integer.parseInt(text); // You can also use Double.parseDouble() for decimals
            return true; // Input is a valid number
        } catch (NumberFormatException e) {
            new ValidationErrorMessageDialog(parentFrame).showMessage("Invalid input. Please enter a valid number.");
            return false; // Input is not a valid number
        }
    }

    public static boolean verify(String text) {
        // Check if the input is a valid number
        try {
            Integer.parseInt(text); // You can also use Double.parseDouble() for decimals
            return true; // Input is a valid number
        } catch (NumberFormatException e) {
            new ValidationErrorMessageDialog(null).showMessage("Invalid input. Please enter a valid number.");
            return false; // Input is not a valid number
        }
    }
}

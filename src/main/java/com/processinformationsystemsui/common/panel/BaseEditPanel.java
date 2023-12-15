package com.processinformationsystemsui.common.panel;

import com.processinformationsystemsui.common.Dimensions;
import com.processinformationsystemsui.common.button.DeleteButton;
import com.processinformationsystemsui.common.button.SaveButton;
import com.processinformationsystemsui.common.dialog.message.ErrorMessageDialog;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;
import com.processinformationsystemsui.common.dialog.message.ValidationErrorMessageDialog;

import javax.swing.*;
import java.awt.*;

public class BaseEditPanel<T> extends JPanel {
    protected InformationMessageDialog informationMessageDialog = new InformationMessageDialog(this);
    protected ValidationErrorMessageDialog validationErrorMessageDialog = new ValidationErrorMessageDialog(this);
    protected ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog(this);
    protected T element;

    public BaseEditPanel(String title, T element, Dimensions dimensions) {
        setLayout(new GridLayout(dimensions.x(), dimensions.y()));

        this.element = element;

        // Create base data panel
        JPanel dataPanel = initializeDataPanel();

        dataPanel.setBorder(BorderFactory.createTitledBorder(title));

        add(dataPanel);

        // Initialize actions
        Runnable saveAction = initializeSaveAction();
        Runnable deleteAction = initializeDeleteAction();

        // Create action buttons panel
        JButton saveButton = new SaveButton(saveAction);
        JButton deleteButton = new DeleteButton(deleteAction);
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Opcije"));
        buttonsPanel.add(saveButton);
        buttonsPanel.add(deleteButton);

        add(buttonsPanel);
    }

    protected JPanel initializeDataPanel() { return null; }
    protected Runnable initializeSaveAction() { return null; }
    protected Runnable initializeDeleteAction() { return null; }
}

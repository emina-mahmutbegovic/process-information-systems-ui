package com.processinformationsystemsui.panel.Gost.Create;

import com.processinformationsystemsui.common.DimensionsEnum;
import com.processinformationsystemsui.common.dialog.create.BaseCreateNewElementDialog;
import com.processinformationsystemsui.panel.Gost.Data.GostDataChangeListener;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateGostDialog extends BaseCreateNewElementDialog {
    private final GostDataChangeListener listener;

    public CreateGostDialog(JFrame parentFrame, GostDataChangeListener listener) {
        super(parentFrame, "Kreiraj novog gosta", DimensionsEnum.fourTimesOne.getDimensions());

        this.listener = listener;

        setLabels();

        setTextFields();
    }

    // Method to send data to the parent panel
    private void sendDataToParent(CreateGostModel data) throws IOException {
        if (listener != null) {
            listener.onGostCreated(data);
        }
    }

    @Override
    protected List<JLabel> setLabels() {
        List<JLabel> labelList = new ArrayList<>();

        labelList.add(new JLabel("Ime gosta:"));
        labelList.add(new JLabel("Prezime gosta:"));
        labelList.add(new JLabel("Biografija gosta:"));
        labelList.add(new JLabel("Kontakt telefon gosta:"));

        return labelList;
    }

    @Override
    protected List<JTextField> setTextFields() {
        // Create four input fields
        JTextField imeGosta = new JTextField(10);
        JTextField prezimeGosta = new JTextField(10);
        JTextField biografijaGosta = new JTextField(10);
        JTextField kontaktTelefonGosta = new JTextField(10);

        List<JTextField> textFields = new ArrayList<>();
        textFields.add(imeGosta);
        textFields.add(prezimeGosta);
        textFields.add(biografijaGosta);
        textFields.add(kontaktTelefonGosta);

        return textFields;
    }

    @Override
    protected void processData(List<String> data) throws IOException {
        // Retrieve values from input fields
        CreateGostModel createGostModel = new CreateGostModel(data.get(0), data.get(1), data.get(2), data.get(3));

        sendDataToParent(createGostModel);
    }
}

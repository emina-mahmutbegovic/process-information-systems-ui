package com.processinformationsystemsui.panel.Voditelj.Create;

import com.processinformationsystemsui.common.DimensionsEnum;
import com.processinformationsystemsui.common.dialog.create.BaseCreateNewElementDialog;
import com.processinformationsystemsui.panel.Voditelj.Data.VoditeljiDataChangeListener;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateVoditeljDialog extends BaseCreateNewElementDialog {
    private final VoditeljiDataChangeListener listener;

    public CreateVoditeljDialog(JFrame parentFrame, VoditeljiDataChangeListener listener) {
        super(parentFrame, "Kreiraj novog voditelja", DimensionsEnum.threeTimesOne.getDimensions());

        this.listener = listener;

        setLabels();

        setTextFields();
    }

    // Method to send data to the parent panel
    private void sendDataToParent(CreateVoditeljModel data) throws IOException {
        if (listener != null) {
            listener.onVoditeljCreated(data);
        }
    }

    @Override
    protected List<JLabel> setLabels() {
        List<JLabel> labelList = new ArrayList<>();

        labelList.add(new JLabel("Ime voditelja:"));
        labelList.add(new JLabel("Prezime voditelja:"));
        labelList.add(new JLabel("Kontakt telefon voditelja:"));

        return labelList;
    }

    @Override
    protected List<JTextField> setTextFields() {
        // Create four input fields
        JTextField imeVoditelja = new JTextField(10);
        JTextField prezimeVoditelja = new JTextField(10);
        JTextField kontaktTelefonVoditelja = new JTextField(10);

        List<JTextField> textFields = new ArrayList<>();
        textFields.add(imeVoditelja);
        textFields.add(prezimeVoditelja);
        textFields.add(kontaktTelefonVoditelja);

        return textFields;
    }

    @Override
    protected void processData(List<String> data) throws IOException {
        // Retrieve values from input fields
        CreateVoditeljModel createVoditeljModel = new CreateVoditeljModel(data.get(0), data.get(1), data.get(2));

        sendDataToParent(createVoditeljModel);
    }
}

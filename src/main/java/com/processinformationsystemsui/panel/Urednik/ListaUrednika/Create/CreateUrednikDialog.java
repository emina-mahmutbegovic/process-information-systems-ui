package com.processinformationsystemsui.panel.Urednik.ListaUrednika.Create;

import com.processinformationsystemsui.common.DimensionsEnum;
import com.processinformationsystemsui.common.dialog.create.BaseCreateNewElementDialog;
import com.processinformationsystemsui.panel.Urednik.Data.UrednikDataChangeListener;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateUrednikDialog extends BaseCreateNewElementDialog {
    private final UrednikDataChangeListener listener;

    public CreateUrednikDialog(JFrame parentFrame, UrednikDataChangeListener listener) {
        super(parentFrame, "Kreiraj novog urednika", DimensionsEnum.threeTimesOne.getDimensions());

        this.listener = listener;

        setLabels();

        setTextFields();
    }

    // Method to send data to the parent panel
    private void sendDataToParent(CreateUrednikModel data) throws IOException {
        if (listener != null) {
            listener.onUrednikCreated(data);
        }
    }

    @Override
    protected List<JLabel> setLabels() {
        List<JLabel> labelList = new ArrayList<>();

        labelList.add(new JLabel("Ime urednika:"));
        labelList.add(new JLabel("Prezime urednika:"));
        labelList.add(new JLabel("Kontakt telefon urednika:"));

        return labelList;
    }

    @Override
    protected List<JTextField> setTextFields() {
        // Create four input fields
        JTextField imeUrednika = new JTextField(10);
        JTextField prezimeUrednika = new JTextField(10);
        JTextField kontaktTelefonUrednika = new JTextField(10);

        List<JTextField> textFields = new ArrayList<>();
        textFields.add(imeUrednika);
        textFields.add(prezimeUrednika);
        textFields.add(kontaktTelefonUrednika);

        return textFields;
    }

    @Override
    protected void processData(List<String> data) throws IOException {
        // Retrieve values from input fields
        CreateUrednikModel createUrednikModel = new CreateUrednikModel(data.get(0), data.get(1), data.get(2));

        sendDataToParent(createUrednikModel);
    }
}

package com.processinformationsystemsui.panel.VrstaEmisije.create;

import com.processinformationsystemsui.common.DimensionsEnum;
import com.processinformationsystemsui.common.dialog.create.BaseCreateNewElementDialog;
import com.processinformationsystemsui.panel.VrstaEmisije.Data.VrstaEmisijeDataChangeListener;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateVrstaEmisijeDialog extends BaseCreateNewElementDialog {
    private final VrstaEmisijeDataChangeListener listener;

    public CreateVrstaEmisijeDialog(JFrame parentFrame, VrstaEmisijeDataChangeListener listener) {
        super(parentFrame, "Kreiraj novu vrstu emisije", DimensionsEnum.oneTimesOne.getDimensions());

        this.listener = listener;

        setLabels();

        setTextFields();
    }

    // Method to send data to the parent panel
    private void sendDataToParent(CreateVrstaEmisijeModel data) throws IOException {
        if (listener != null) {
            listener.onVrstaEmisijeCreated(data);
        }
    }

    @Override
    protected List<JLabel> setLabels() {
        List<JLabel> labelList = new ArrayList<>();

        labelList.add(new JLabel("Naziv vrste emisije:"));

        return labelList;
    }

    @Override
    protected List<JTextField> setTextFields() {
        // Create four input fields
        JTextField nazivVrsteEmisije = new JTextField(10);

        List<JTextField> textFields = new ArrayList<>();
        textFields.add(nazivVrsteEmisije);

        return textFields;
    }

    @Override
    protected void processData(List<String> data) throws IOException {
        // Retrieve values from input fields
        CreateVrstaEmisijeModel createVrstaEmisijeModel = new CreateVrstaEmisijeModel(data.get(0));

        sendDataToParent(createVrstaEmisijeModel);
    }
}

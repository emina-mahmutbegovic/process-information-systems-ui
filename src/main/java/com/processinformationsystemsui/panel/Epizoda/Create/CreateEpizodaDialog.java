package com.processinformationsystemsui.panel.Epizoda.Create;

import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.common.NumberInputVerifier;
import com.processinformationsystemsui.common.dialog.create.BaseCreateNewElementDialog;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CreateEpizodaDialog extends BaseCreateNewElementDialog {
    private final EmisijaDataChangeListener listener;

    public CreateEpizodaDialog(JFrame parentFrame, EmisijaDataChangeListener listener) {
        super(parentFrame, "Kreiraj novu epizodu", 4, 2);

        this.listener = listener;

        setLabels();

        setTextFields();
    }

    // Method to send data to the parent panel
    private void sendDataToParent(CreateEpizodaModel data) {
        if (listener != null) {
            listener.onEpizodaAdded(data);
        }
    }

    @Override
    protected List<JLabel> setLabels() {
        List<JLabel> labelList = new ArrayList<>();

        labelList.add(new JLabel("Naziv epizode:"));
        labelList.add(new JLabel("Broj epizode:"));
        labelList.add(new JLabel("Broj sezone:"));
        labelList.add(new JLabel("Opis epizode:"));

        return labelList;
    }

    @Override
    protected List<JTextField> setTextFields() {
        // Create four input fields
        JTextField nazivEpizode = new JTextField(10);
        JTextField brojEpizode = new JTextField(10);
        JTextField brojSezone = new JTextField(10);
        JTextField opisEpizode = new JTextField(10);

        brojEpizode.setInputVerifier(new NumberInputVerifier());
        brojSezone.setInputVerifier(new NumberInputVerifier());

        List<JTextField> textFields = new ArrayList<>();
        textFields.add(nazivEpizode);
        textFields.add(brojEpizode);
        textFields.add(brojSezone);
        textFields.add(opisEpizode);

        return textFields;
    }

    @Override
    protected void processData(List<String> data) {
        // Retrieve values from input fields
        CreateEpizodaModel createEpizodaModel = new CreateEpizodaModel(
                data.get(0),
                Integer.parseInt(data.get(1)),
                Integer.parseInt(data.get(2)),
                data.get(3),
                ""
        );

        sendDataToParent(createEpizodaModel);
    }
}

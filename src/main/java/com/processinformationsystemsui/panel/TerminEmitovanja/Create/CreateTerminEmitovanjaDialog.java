package com.processinformationsystemsui.panel.TerminEmitovanja.Create;

import com.processinformationsystemsui.common.Dimensions;
import com.processinformationsystemsui.common.DimensionsEnum;
import com.processinformationsystemsui.panel.Epizoda.Data.EpizodaDataChangeListener;
import com.processinformationsystemsui.common.dialog.create.BaseCreateNewElementDialog;
import com.processinformationsystemsui.model.EpizodaModel;

import javax.swing.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateTerminEmitovanjaDialog extends BaseCreateNewElementDialog {
    private final EpizodaDataChangeListener listener;

    public CreateTerminEmitovanjaDialog(JFrame parentFrame, EpizodaDataChangeListener listener, EpizodaModel epizoda) {
        super(parentFrame, String.format("Kreiraj novi termin emitovanja za epizodu: %s", epizoda.getNazivEpizode()), DimensionsEnum.threeTimesOne.getDimensions());

        this.listener = listener;

        setLabels();

        setTextFields();
    }

    // Method to send data to the parent panel
    private void sendDataToParent(CreateTerminEmitovanjaModel data) {
        if (listener != null) {
            listener.onTerminEmitovanjaAdded(data);
        }
    }

    @Override
    protected List<JLabel> setLabels() {
        List<JLabel> labelList = new ArrayList<>();

        labelList.add(new JLabel("Vrijeme početka:"));
        labelList.add(new JLabel("Vrijeme završetka:"));
        labelList.add(new JLabel("Datum emitovanja:"));

        return labelList;
    }

    @Override
    protected List<JTextField> setTextFields() {
        // Create four input fields
        JTextField vrijemePocetka = new JTextField(10);
        JTextField vrijemeZavrsetka = new JTextField(10);
        JTextField datumEmitovanja = new JTextField(10);

        List<JTextField> textFields = new ArrayList<>();
        textFields.add(vrijemePocetka);
        textFields.add(vrijemeZavrsetka);
        textFields.add(datumEmitovanja);

        return textFields;
    }

    @Override
    protected void processData(List<String> data) throws ParseException {
        // Format inputs
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date parsedVrijemePocetka = timeFormat.parse(data.get(0));
        Time vrijemePocetka = new Time(parsedVrijemePocetka.getTime());

        Date parsedVrijemeZavrsetka = timeFormat.parse(data.get(1));
        Time vrijemeZavrsetka = new Time(parsedVrijemeZavrsetka.getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(data.get(2));
        java.sql.Date datumEmitovanja = new java.sql.Date(parsedDate.getTime());


        // Retrieve values from input fields
        CreateTerminEmitovanjaModel createTerminEmitovanjaModel = new CreateTerminEmitovanjaModel(
                vrijemePocetka,
                vrijemeZavrsetka,
                datumEmitovanja,
                ""
        );

        sendDataToParent(createTerminEmitovanjaModel);
    }
}

package com.processinformationsystemsui.panel.Urednik;

import com.processinformationsystemsui.common.DimensionsEnum;
import com.processinformationsystemsui.common.panel.BaseEditPanel;
import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.panel.Urednik.Data.UrednikDataChangeListener;
import com.processinformatuionsystemsui.api.UrednikApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditUrednikPanel extends BaseEditPanel<UrednikModel> {
    private JTextField imeUrednikaValue;
    private JTextField prezimeUrednikaValue;
    private JTextField kontaktTelefonUrednikaValue;
    private final UrednikApiResources apiResources = new UrednikApiResources();
    private final UrednikDataChangeListener listener;

    public EditUrednikPanel(UrednikModel urednik, UrednikDataChangeListener listener) {
        super("Podaci o uredniku", urednik, DimensionsEnum.twoTimesOne.getDimensions());

        this.listener = listener;
    }

    @Override
    protected JPanel initializeDataPanel() {
        JLabel imeUrednika = new JLabel("Ime urednika");
        imeUrednikaValue = new JTextField(element.getImeUrednika());

        JLabel prezimeUrednika = new JLabel("Prezime urednika");
        prezimeUrednikaValue = new JTextField(element.getPrezimeUrednika());

        JLabel kontaktTelefonUrednika = new JLabel("Kontakt telefon urednika");
        kontaktTelefonUrednikaValue = new JTextField(element.getKontaktTelefonUrednika());

        JPanel dataPanel = new JPanel(new GridLayout(3, 2));
        dataPanel.add(imeUrednika);
        dataPanel.add(imeUrednikaValue);
        dataPanel.add(prezimeUrednika);
        dataPanel.add(prezimeUrednikaValue);
        dataPanel.add(kontaktTelefonUrednika);
        dataPanel.add(kontaktTelefonUrednikaValue);

        return dataPanel;
    }

    @Override
    protected Runnable initializeSaveAction() {
        return () -> {
            if(imeUrednikaValue.getText().isEmpty() || prezimeUrednikaValue.getText().isEmpty() || kontaktTelefonUrednikaValue.getText().isEmpty()) {
                validationErrorMessageDialog.showMessage("Polja ne smiju biti prazna!");
            }
            else {
                try {
                    UrednikModel newUrednik = new UrednikModel(element.getIdUrednika(),
                            imeUrednikaValue.getText(), prezimeUrednikaValue.getText(), kontaktTelefonUrednikaValue.getText());

                    element = apiResources.updateUrednik(newUrednik, element.getIdUrednika());

                    informationMessageDialog.showMessage("Urednik uspješno ažuriran!");

                    listener.onUrednikEdited();

                    // Update frame title
                    ((JFrame) SwingUtilities.getWindowAncestor(this)).setTitle(String.format("%s %s", element.getImeUrednika(), element.getPrezimeUrednika()));
                } catch (IOException ex) {
                    errorMessageDialog.showMessage(String.format("Greška prilikom snimanja urednika: %s", ex));
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    @Override
    protected Runnable initializeDeleteAction() {
        return () -> {
            try {
                apiResources.deleteUrednik(element.getIdUrednika());

                listener.onUrednikDeleted();

                informationMessageDialog.showMessage(String.format("Urednik %s %s uspješno obrisan!", element.getImeUrednika(), element.getPrezimeUrednika()));
            } catch (IOException ex) {
                errorMessageDialog.showMessage(String.format("Greška prilikom brisanja urednika: %s", ex));
                throw new RuntimeException(ex);
            }
        };
    }
}

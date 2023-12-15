package com.processinformationsystemsui.panel.Gost.Edit;

import com.processinformationsystemsui.common.DimensionsEnum;
import com.processinformationsystemsui.common.panel.BaseEditPanel;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.common.Common;
import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.panel.Gost.Data.GostDataChangeListener;
import com.processinformatuionsystemsui.api.GostApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditGostPanel extends BaseEditPanel<GostModel> {
    private JTextField imeGostaValue;
    private JTextField prezimeGostaValue;
    private JTextField biografijaGostaValue;
    private JTextField kontaktTelefonGostaValue;

    private final GostApiResources apiResources = new GostApiResources();

    private final GostDataChangeListener listener;

    public EditGostPanel(GostModel gost, GostDataChangeListener listener) {
        super("Podaci o gostu", gost, DimensionsEnum.twoTimesOne.getDimensions());

        this.listener = listener;
    }

    @Override
    protected JPanel initializeDataPanel() {
        JLabel imeGosta = new JLabel("Ime gosta");
        imeGostaValue = new JTextField(element.getImeGosta());

        JLabel prezimeGosta = new JLabel("Prezime gosta");
        prezimeGostaValue = new JTextField(element.getPrezimeGosta());

        JLabel biografijaGosta = new JLabel("Biografija gosta");
        biografijaGostaValue = new JTextField(element.getBiografijaGosta());

        JLabel kontaktTelefonGosta = new JLabel("Kontakt telefon gosta");
        kontaktTelefonGostaValue = new JTextField(element.getKontaktTelefonGosta());

        JPanel dataPanel = new JPanel(new GridLayout(4, 2));
        dataPanel.add(imeGosta);
        dataPanel.add(imeGostaValue);
        dataPanel.add(prezimeGosta);
        dataPanel.add(prezimeGostaValue);
        dataPanel.add(biografijaGosta);
        dataPanel.add(biografijaGostaValue);
        dataPanel.add(kontaktTelefonGosta);
        dataPanel.add(kontaktTelefonGostaValue);

        return dataPanel;
    }

    @Override
    protected Runnable initializeSaveAction() {
        return () ->  {
            if(imeGostaValue.getText().isEmpty() || prezimeGostaValue.getText().isEmpty() ||
                    biografijaGostaValue.getText().isEmpty() || kontaktTelefonGostaValue.getText().isEmpty()) {
                validationErrorMessageDialog.showMessage("Polja ne smiju biti prazna!");
            }
            else {
                try {
                    GostModel newGost = new GostModel(element.getIdGosta(),
                            imeGostaValue.getText(), prezimeGostaValue.getText(), biografijaGostaValue.getText(), kontaktTelefonGostaValue.getText());

                    element = apiResources.updateGost(newGost, element.getIdGosta());
                    informationMessageDialog.showMessage("Gost uspješno ažuriran!");

                    // Emit edit event
                    listener.onGostEdited();

                    // Update frame title
                    ((JFrame) SwingUtilities.getWindowAncestor(this)).setTitle(String.format("%s %s", element.getImeGosta(), element.getPrezimeGosta()));
                } catch (IOException ex) {
                    errorMessageDialog.showMessage(String.format("Greška prilikom snimanja gosta: %s", ex));
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    @Override
    protected Runnable initializeDeleteAction() {
        return () -> {
            try {
                apiResources.deleteGost(element.getIdGosta());

                // Emit delete event
                listener.onGostDeleted();

                informationMessageDialog.showMessage(String.format("Gost %s %s uspješno obrisan!", element.getImeGosta(), element.getPrezimeGosta()));
            } catch (IOException ex) {
                errorMessageDialog.showMessage(String.format("Greška prilikom brisanja gosta: %s", ex));
                throw new RuntimeException(ex);
            }
        };
    }
}

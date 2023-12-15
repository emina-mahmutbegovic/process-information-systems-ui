package com.processinformationsystemsui.panel.Voditelj;

import com.processinformationsystemsui.common.Dimensions;
import com.processinformationsystemsui.common.panel.BaseEditPanel;
import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.panel.Voditelj.Data.VoditeljiDataChangeListener;
import com.processinformatuionsystemsui.api.VoditeljApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditVoditeljPanel extends BaseEditPanel<VoditeljModel> {
    private JTextField imeVoditeljaValue;
    private JTextField prezimeVoditeljaValue;
    private JTextField kontaktTelefonVoditeljaValue;

    private final VoditeljApiResources apiResources = new VoditeljApiResources();
    private final VoditeljiDataChangeListener listener;

    public EditVoditeljPanel(VoditeljModel voditelj, VoditeljiDataChangeListener listener) {
        super("Podaci o voditelju", voditelj, new Dimensions(2, 1));

        this.listener = listener;
    }

    @Override
    protected JPanel initializeDataPanel() {
        JLabel imeVoditelja = new JLabel("Ime voditelja");
        imeVoditeljaValue = new JTextField(element.getImeVoditelja());

        JLabel prezimeVoditelja = new JLabel("Prezime voditelja");
        prezimeVoditeljaValue = new JTextField(element.getPrezimeVoditelja());

        JLabel kontaktTelefonVoditelja = new JLabel("Kontakt telefon voditelja");
        kontaktTelefonVoditeljaValue = new JTextField(element.getKontaktTelefonVoditelja());

        JPanel dataPanel = new JPanel(new GridLayout(3, 2));
        dataPanel.add(imeVoditelja);
        dataPanel.add(imeVoditeljaValue);
        dataPanel.add(prezimeVoditelja);
        dataPanel.add(prezimeVoditeljaValue);
        dataPanel.add(kontaktTelefonVoditelja);
        dataPanel.add(kontaktTelefonVoditeljaValue);

        return dataPanel;
    }

    @Override
    protected Runnable initializeSaveAction() {
        return () -> {
            if(imeVoditeljaValue.getText().isEmpty() || prezimeVoditeljaValue.getText().isEmpty() || kontaktTelefonVoditeljaValue.getText().isEmpty()) {
                validationErrorMessageDialog.showMessage("Polja ne smiju biti prazna!");
            }
            else {
                try {
                    VoditeljModel newVoditelj = new VoditeljModel(element.getIdVoditelja(),
                            imeVoditeljaValue.getText(), prezimeVoditeljaValue.getText(), kontaktTelefonVoditeljaValue.getText());
                    element = apiResources.updateVoditelj(newVoditelj, element.getIdVoditelja());
                    informationMessageDialog.showMessage("Voditelj uspješno ažuriran!");

                    listener.onVoditeljEdited();
                    // Update frame title
                    ((JFrame) SwingUtilities.getWindowAncestor(this)).setTitle(String.format("%s %s", element.getImeVoditelja(), element.getPrezimeVoditelja()));
                } catch (IOException ex) {
                    errorMessageDialog.showMessage(String.format("Greška prilikom snimanja voditelja: %s", ex));
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    @Override
    protected Runnable initializeDeleteAction() {
        return () -> {
            try {
                apiResources.deleteVoditelj(element.getIdVoditelja());
                informationMessageDialog.showMessage(String.format("Voditelj %s %s uspješno obrisan!", element.getImeVoditelja(), element.getPrezimeVoditelja()));

                //Emit delete event
                listener.onVoditeljDeleted();

                // Dispose frame
                SwingUtilities.getWindowAncestor(this).dispose();
            } catch (IOException ex) {
                errorMessageDialog.showMessage(String.format("Greška prilikom brisanja vrste emisije: %s", ex));
                throw new RuntimeException(ex);
            }
        };
    }
}

package com.processinformationsystemsui.panel.VrstaEmisije.Edit;

import com.processinformationsystemsui.common.Dimensions;
import com.processinformationsystemsui.common.DimensionsEnum;
import com.processinformationsystemsui.common.panel.BaseEditPanel;
import com.processinformationsystemsui.model.VrstaEmisijeModel;
import com.processinformationsystemsui.panel.VrstaEmisije.Data.VrstaEmisijeDataChangeListener;
import com.processinformatuionsystemsui.api.VrstaEmisijeApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditVrstaEmisijePanel extends BaseEditPanel<VrstaEmisijeModel> {
    private JTextField nazivVrsteEmisijeValue;
    private final VrstaEmisijeApiResources apiResources = new VrstaEmisijeApiResources();
    private final VrstaEmisijeDataChangeListener listener;

    public EditVrstaEmisijePanel(VrstaEmisijeModel vrstaEmisije, VrstaEmisijeDataChangeListener listener) {
        super(String.format("Uredi vrstu emisije %s", vrstaEmisije.getNazivVrsteEmisije()), vrstaEmisije,  DimensionsEnum.twoTimesOne.getDimensions());

        this.listener = listener;
    }

    @Override
    protected JPanel initializeDataPanel() {
        JLabel nazivVrsteEmisije = new JLabel("Naziv vrste emisije");
        nazivVrsteEmisijeValue = new JTextField(element.getNazivVrsteEmisije());

        JPanel dataPanel = new JPanel(new GridLayout(1, 2));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(nazivVrsteEmisije);
        dataPanel.add(labelPanel);

        JPanel textFieldPanel = new JPanel(new FlowLayout());
        textFieldPanel.add(nazivVrsteEmisijeValue);
        dataPanel.add(textFieldPanel);

        return dataPanel;
    }

    @Override
    protected Runnable initializeSaveAction() {
        return () -> {
            if (nazivVrsteEmisijeValue.getText().isEmpty() || nazivVrsteEmisijeValue.getText().isBlank()) {
                validationErrorMessageDialog.showMessage("Polja ne smiju biti prazna!");
            } else {
                try {
                    VrstaEmisijeModel newVrstaEmisije = new VrstaEmisijeModel(nazivVrsteEmisijeValue.getText(), element.getIdVrsteEmisije());

                    element = apiResources.updateVrstaEmisijeModel(newVrstaEmisije, element.getIdVrsteEmisije());
                    informationMessageDialog.showMessage("Vrsta emisije uspješno ažurirana!");

                    // Update list
                    listener.onVrstaEmisijeEdited();
                    // Update frame title
                    ((JFrame) SwingUtilities.getWindowAncestor(this)).setTitle(element.getNazivVrsteEmisije());
                } catch (IOException ex) {
                    errorMessageDialog.showMessage(String.format("Greška prilikom snimanja vrste emisije: %s", ex));
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    @Override
    protected Runnable initializeDeleteAction() {
        return () -> {
            try {
                apiResources.deleteVrstaEmisije(element.getIdVrsteEmisije());
                informationMessageDialog.showMessage(String.format("Vrsta emisije %s uspješno obrisana!", element.getNazivVrsteEmisije()));

                //Emit delete event
                listener.onVrstaEmisijeDeleted();

                // Dispose frame
                SwingUtilities.getWindowAncestor(this).dispose();
            } catch (IOException ex) {
                errorMessageDialog.showMessage(String.format("Greška prilikom brisanja vrste emisije: %s", ex));
                throw new RuntimeException(ex);
            }
        };
    }
}

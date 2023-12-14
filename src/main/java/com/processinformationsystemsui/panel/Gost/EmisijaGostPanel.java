package com.processinformationsystemsui.panel.Gost;

import com.processinformationsystemsui.common.button.DeleteButton;
import com.processinformationsystemsui.common.dialog.message.ErrorMessageDialog;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformatuionsystemsui.api.GostujeApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EmisijaGostPanel extends GostPanelBase {
    private final GostujeApiResources gostujeApiResources = new GostujeApiResources();
    private final String idEmisije;
    private final EmisijaDataChangeListener listener;

    private final InformationMessageDialog informationMessageDialog = new InformationMessageDialog(EmisijaGostPanel.this);
    private final ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog(EmisijaGostPanel.this);
    public EmisijaGostPanel(GostModel gost, String idEmisije, EmisijaDataChangeListener listener) {
        super(gost);

        this.idEmisije = idEmisije;
        this.listener = listener;
    }

    @Override
    protected void createActionButtons() {
        // Add action buttons
        Runnable deleteAction = () -> {
            try {
                gostujeApiResources.deleteGostujeByIdGostaAndIdEmisije(gost.getIdGosta(), idEmisije);

                this.listener.onGostDeleted();

                informationMessageDialog.showMessage(String.format("Gost %s %s uspješno obrisan sa emisije!", gost.getImeGosta(), gost.getPrezimeGosta()));
                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);

                parent.dispose();
            } catch (IOException ex) {
                errorMessageDialog.showMessage(String.format("Greška prilikom brisanja gosta: %s", ex));

                throw new RuntimeException(ex);
            }
        };

        JButton deleteButton = new DeleteButton(deleteAction);

        this.buttonsPanel.setBorder(BorderFactory.createTitledBorder("Dodatne opcije"));
        buttonsPanel.add(deleteButton);
    }
}

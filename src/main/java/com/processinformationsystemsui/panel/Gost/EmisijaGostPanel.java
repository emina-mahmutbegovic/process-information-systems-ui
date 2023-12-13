package com.processinformationsystemsui.panel.Gost;

import com.processinformationsystemsui.common.dialog.message.ErrorMessageDialog;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.common.Common;
import com.processinformatuionsystemsui.api.GostujeApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EmisijaGostPanel extends GostPanelBase {
    private final GostujeApiResources gostujeApiResources = new GostujeApiResources();
    private final String idEmisije;

    private final InformationMessageDialog informationMessageDialog = new InformationMessageDialog(EmisijaGostPanel.this);
    private final ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog(EmisijaGostPanel.this);
    public EmisijaGostPanel(GostModel gost, String idEmisije) {
        super(gost);

        this.idEmisije = idEmisije;
    }

    @Override
    protected void createActionButtons() {
        // Add action buttons
        JButton deleteButton = new JButton("DELETE");
        deleteButton.setForeground(Color.red);

        Common.addMouseListener(deleteButton);

        deleteButton.addActionListener(e -> {
            try {
                gostujeApiResources.deleteGostujeByIdGostaAndIdEmisije(gost.getIdGosta(), idEmisije);

                informationMessageDialog.showMessage(String.format("Gost %s %s uspješno obrisan sa emisije!", gost.getImeGosta(), gost.getPrezimeGosta()));
                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);

                parent.dispose();
            } catch (IOException ex) {
                errorMessageDialog.showMessage(String.format("Greška prilikom brisanja gosta: %s", ex));

                throw new RuntimeException(ex);
            }
        });

        this.buttonsPanel.setBorder(BorderFactory.createTitledBorder("Dodatne opcije"));
        buttonsPanel.add(deleteButton);
    }
}

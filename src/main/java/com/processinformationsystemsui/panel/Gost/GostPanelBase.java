package com.processinformationsystemsui.panel.Gost;

import com.processinformationsystemsui.common.dialog.message.ErrorMessageDialog;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.panel.Gost.Edit.EditGost;
import com.processinformationsystemsui.common.Common;
import com.processinformatuionsystemsui.api.GostApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GostPanelBase extends JPanel {
    final JPanel buttonsPanel = new JPanel(new FlowLayout());
    final GostModel gost;
    private final GostApiResources apiResources = new GostApiResources();
    private final InformationMessageDialog informationMessageDialog = new InformationMessageDialog(GostPanelBase.this);
    private final ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog(GostPanelBase.this);

    public GostPanelBase(GostModel gost) {
        this.gost = gost;

        setLayout(new GridLayout(2, 1));

        // Add biography
        JTextArea biografijaGostaValue = new JTextArea(gost.getBiografijaGosta());
        biografijaGostaValue.setEditable(false);
        biografijaGostaValue.setLineWrap(true); // Enable word wrap
        biografijaGostaValue.setWrapStyleWord(true); // Wrap at word boundaries
        biografijaGostaValue.setOpaque(false);

        JPanel labelsPanel = new JPanel(new BorderLayout());
        labelsPanel.setBorder(BorderFactory.createTitledBorder("Biografija"));
        labelsPanel.add(biografijaGostaValue, BorderLayout.NORTH);

        add(labelsPanel);

        // Add contact
        JPanel contactAndButtons = new JPanel();
        contactAndButtons.setLayout(new GridLayout(1,2));

        // Add contact info
        JPanel kontaktTelefonPanel = new JPanel();
        JLabel kontaktTelefonLabel = new JLabel(gost.getKontaktTelefonGosta());
        kontaktTelefonPanel.setBorder(BorderFactory.createTitledBorder("Kontakt telefon"));
        kontaktTelefonPanel.add(kontaktTelefonLabel);

        contactAndButtons.add(kontaktTelefonPanel);

        createActionButtons();
        contactAndButtons.add(buttonsPanel);

        add(contactAndButtons);
    }

    protected void createActionButtons() {
        // Add action buttons
        JButton editButton = new JButton("EDIT");
        JButton deleteButton = new JButton("DELETE");
        deleteButton.setForeground(Color.red);

        Common.addMouseListener(editButton);
        Common.addMouseListener(deleteButton);

        editButton.addActionListener(e -> {
            new EditGost(gost);
        });

        deleteButton.addActionListener(e -> {
            try {
                apiResources.deleteGost(gost.getIdGosta());
                informationMessageDialog.showMessage(String.format("Gost %s %s uspješno obrisan!", gost.getImeGosta(), gost.getPrezimeGosta()));
            } catch (IOException ex) {
                errorMessageDialog.showMessage(String.format("Greška prilikom brisanja gosta: %s", ex));
                throw new RuntimeException(ex);
            }
        });

        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Dodatne opcije"));
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
    }
}

package com.processinformationsystemsui.panel.Gost.Edit;

import com.processinformationsystemsui.common.dialog.message.ErrorMessageDialog;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;
import com.processinformationsystemsui.common.dialog.message.ValidationErrorMessageDialog;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.common.Common;
import com.processinformatuionsystemsui.api.GostApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditGostPanel extends JPanel {

    private final GostApiResources apiResources = new GostApiResources();

    private final InformationMessageDialog informationMessageDialog = new InformationMessageDialog(EditGostPanel.this);
    private final ValidationErrorMessageDialog validationErrorMessageDialog = new ValidationErrorMessageDialog(EditGostPanel.this);
    private final ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog(EditGostPanel.this);

    public EditGostPanel(GostModel gost, JFrame parentFrame) {

        setLayout(new GridLayout(2, 1));

        // Create base data panel
        JLabel imeGosta = new JLabel("Ime gosta");
        JTextField imeGostaValue = new JTextField(gost.getImeGosta());

        JLabel prezimeGosta = new JLabel("Prezime gosta");
        JTextField prezimeGostaValue = new JTextField(gost.getPrezimeGosta());

        JLabel biografijaGosta = new JLabel("Biografija gosta");
        JTextField biografijaGostaValue = new JTextField(gost.getBiografijaGosta());

        JLabel kontaktTelefonGosta = new JLabel("Kontakt telefon gosta");
        JTextField kontaktTelefonGostaValue = new JTextField(gost.getKontaktTelefonGosta());

        JPanel dataPanel = new JPanel(new GridLayout(4, 2));
        dataPanel.add(imeGosta);
        dataPanel.add(imeGostaValue);
        dataPanel.add(prezimeGosta);
        dataPanel.add(prezimeGostaValue);
        dataPanel.add(biografijaGosta);
        dataPanel.add(biografijaGostaValue);
        dataPanel.add(kontaktTelefonGosta);
        dataPanel.add(kontaktTelefonGostaValue);
        dataPanel.setBorder(BorderFactory.createTitledBorder("Podaci o gostu"));

        add(dataPanel);

        // Create action buttons panel
        JButton saveButton = new JButton("SAVE");
        Common.addMouseListener(saveButton);
        JButton getAllButton = new JButton("DISPLAY GUESTS");
        Common.addMouseListener(getAllButton);
        JButton deleteButton = new JButton("DELETE");
        Common.addMouseListener(deleteButton);
        deleteButton.setForeground(Color.RED);
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        // Define action listeners
        getAllButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Get all clicked");
        });

        saveButton.addActionListener(e -> {
            if(imeGostaValue.getText().isEmpty() || prezimeGostaValue.getText().isEmpty() ||
                    biografijaGostaValue.getText().isEmpty() || kontaktTelefonGostaValue.getText().isEmpty()) {
                validationErrorMessageDialog.showMessage("Polja ne smiju biti prazna!");
            }
            else {
                GostModel newGost = new GostModel(gost.getIdGosta(),
                        imeGostaValue.getText(), prezimeGostaValue.getText(), biografijaGostaValue.getText(), kontaktTelefonGostaValue.getText());
                try {
                    GostModel updatedGost = apiResources.updateGost(newGost, gost.getIdGosta());
                    informationMessageDialog.showMessage("Gost uspješno ažuriran!");
                    // Update frame title
                    parentFrame.setTitle(String.format("%s %s", updatedGost.getImeGosta(), updatedGost.getPrezimeGosta()));
                } catch (IOException ex) {
                    errorMessageDialog.showMessage(String.format("Greška prilikom snimanja gosta: %s", ex));
                    throw new RuntimeException(ex);
                }
            }
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

        buttonsPanel.add(getAllButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(deleteButton);

        add(buttonsPanel);
    }
}

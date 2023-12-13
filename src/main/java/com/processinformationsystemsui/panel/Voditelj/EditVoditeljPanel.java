package com.processinformationsystemsui.panel.Voditelj;

import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.common.Common;
import com.processinformatuionsystemsui.api.VoditeljApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditVoditeljPanel extends JPanel {
    private final VoditeljApiResources apiResources = new VoditeljApiResources();

    public EditVoditeljPanel(VoditeljModel voditelj, JFrame parentFrame) {

        setLayout(new GridLayout(2, 1));

        // Create base data panel
        JLabel imeVoditelja = new JLabel("Ime voditelja");
        JTextField imeVoditeljaValue = new JTextField(voditelj.getImeVoditelja());

        JLabel prezimeVoditelja = new JLabel("Prezime voditelja");
        JTextField prezimeVoditeljaValue = new JTextField(voditelj.getPrezimeVoditelja());

        JLabel kontaktTelefonVoditelja = new JLabel("Kontakt telefon voditelja");
        JTextField kontaktTelefonVoditeljaValue = new JTextField(voditelj.getKontaktTelefonVoditelja());

        JPanel dataPanel = new JPanel(new GridLayout(3, 2));
        dataPanel.add(imeVoditelja);
        dataPanel.add(imeVoditeljaValue);
        dataPanel.add(prezimeVoditelja);
        dataPanel.add(prezimeVoditeljaValue);
        dataPanel.add(kontaktTelefonVoditelja);
        dataPanel.add(kontaktTelefonVoditeljaValue);
        dataPanel.setBorder(BorderFactory.createTitledBorder("Podaci o voditelju"));

        add(dataPanel);

        // Create action buttons panel
        JButton saveButton = new JButton("SAVE");
        Common.addMouseListener(saveButton);
        JButton getAllButton = new JButton("DISPLAY HOSTS");
        Common.addMouseListener(getAllButton);
        JButton deleteButton = new JButton("DELETE");
        Common.addMouseListener(deleteButton);
        deleteButton.setForeground(Color.RED);
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        // Define action listeners
        getAllButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(EditVoditeljPanel.this, "Get all clicked");
//            try {
//                new ListaVoditelja(lis);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
        });

        saveButton.addActionListener(e -> {
            if(imeVoditeljaValue.getText().isEmpty() || prezimeVoditeljaValue.getText().isEmpty() || kontaktTelefonVoditeljaValue.getText().isEmpty()) {
                JOptionPane.showMessageDialog(EditVoditeljPanel.this, "Polja ne smiju biti prazna!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                VoditeljModel newVoditelj = new VoditeljModel(voditelj.getIdVoditelja(),
                        imeVoditeljaValue.getText(), prezimeVoditeljaValue.getText(), kontaktTelefonVoditeljaValue.getText());
                try {
                    VoditeljModel updatedVoditelj = apiResources.updateVoditelj(newVoditelj, voditelj.getIdVoditelja());
                    JOptionPane.showMessageDialog(EditVoditeljPanel.this, "Voditelj uspješno ažuriran!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Update frame title
                    parentFrame.setTitle(String.format("%s %s", updatedVoditelj.getImeVoditelja(), updatedVoditelj.getPrezimeVoditelja()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(EditVoditeljPanel.this,
                            String.format("Greška prilikom snimanja voditelja: %s", ex), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                apiResources.deleteVoditelj(voditelj.getIdVoditelja());
                JOptionPane.showMessageDialog(EditVoditeljPanel.this,
                        String.format("Voditelj %s %s uspješno obrisan!", voditelj.getImeVoditelja(), voditelj.getPrezimeVoditelja()), "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(EditVoditeljPanel.this,
                        String.format("Greška prilikom brisanja voditelja: %s", ex), "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });

        buttonsPanel.add(getAllButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(deleteButton);

        add(buttonsPanel);
    }
}

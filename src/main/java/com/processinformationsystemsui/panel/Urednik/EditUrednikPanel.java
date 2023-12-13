package com.processinformationsystemsui.panel.Urednik;

import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.common.Common;
import com.processinformatuionsystemsui.api.UrednikApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditUrednikPanel extends JPanel {
    private final UrednikApiResources apiResources = new UrednikApiResources();

    public EditUrednikPanel(UrednikModel urednik, JFrame parentFrame) {

        setLayout(new GridLayout(2, 1));

        // Create base data panel
        JLabel imeUrednika = new JLabel("Ime urednika");
        JTextField imeUrednikaValue = new JTextField(urednik.getImeUrednika());

        JLabel prezimeUrednika = new JLabel("Prezime urednika");
        JTextField prezimeUrednikaValue = new JTextField(urednik.getPrezimeUrednika());

        JLabel kontaktTelefonUrednika = new JLabel("Kontakt telefon urednika");
        JTextField kontaktTelefonUrednikaValue = new JTextField(urednik.getKontaktTelefonUrednika());

        JPanel dataPanel = new JPanel(new GridLayout(3, 2));
        dataPanel.add(imeUrednika);
        dataPanel.add(imeUrednikaValue);
        dataPanel.add(prezimeUrednika);
        dataPanel.add(prezimeUrednikaValue);
        dataPanel.add(kontaktTelefonUrednika);
        dataPanel.add(kontaktTelefonUrednikaValue);
        dataPanel.setBorder(BorderFactory.createTitledBorder("Podaci o uredniku"));

        add(dataPanel);

        // Create action buttons panel
        JButton saveButton = new JButton("SAVE");
        Common.addMouseListener(saveButton);
        JButton getAllButton = new JButton("DISPLAY EDITORS");
        Common.addMouseListener(getAllButton);
        JButton deleteButton = new JButton("DELETE");
        Common.addMouseListener(deleteButton);
        deleteButton.setForeground(Color.RED);
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        // Define action listeners
        getAllButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(EditUrednikPanel.this, "Get all button clicked");
//            try {
//                new ListaUrednika();
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
        });

        saveButton.addActionListener(e -> {
            if(imeUrednikaValue.getText().isEmpty() || prezimeUrednikaValue.getText().isEmpty() || kontaktTelefonUrednikaValue.getText().isEmpty()) {
                JOptionPane.showMessageDialog(EditUrednikPanel.this, "Polja ne smiju biti prazna!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                UrednikModel newUrednik = new UrednikModel(urednik.getIdUrednika(),
                        imeUrednikaValue.getText(), prezimeUrednikaValue.getText(), kontaktTelefonUrednikaValue.getText());
                try {
                    UrednikModel updateUrednik = apiResources.updateUrednik(newUrednik, urednik.getIdUrednika());
                    JOptionPane.showMessageDialog(EditUrednikPanel.this, "Urednik uspješno ažuriran!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Update frame title
                    parentFrame.setTitle(String.format("%s %s", updateUrednik.getImeUrednika(), updateUrednik.getPrezimeUrednika()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(EditUrednikPanel.this,
                            String.format("Greška prilikom snimanja urednika: %s", ex), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                apiResources.deleteUrednik(urednik.getIdUrednika());
                JOptionPane.showMessageDialog(EditUrednikPanel.this,
                        String.format("Urednik %s %s uspješno obrisan!", urednik.getImeUrednika(), urednik.getPrezimeUrednika()), "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(EditUrednikPanel.this,
                        String.format("Greška prilikom brisanja urednika: %s", ex), "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });

        buttonsPanel.add(getAllButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(deleteButton);

        add(buttonsPanel);
    }
}

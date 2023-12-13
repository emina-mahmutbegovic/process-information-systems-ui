package com.processinformationsystemsui.panel.VrstaEmisije;

import com.processinformationsystemsui.model.VrstaEmisijeModel;
import com.processinformationsystemsui.common.Common;
import com.processinformatuionsystemsui.api.VrstaEmisijeApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditVrstaEmisijePanel extends JPanel {
    private final VrstaEmisijeApiResources apiResources = new VrstaEmisijeApiResources();

    public EditVrstaEmisijePanel(VrstaEmisijeModel vrstaEmisije, JFrame parentFrame) {

        setLayout(new GridLayout(2, 1));

        // Create base data panel
        JLabel nazivVrsteEmisije = new JLabel("Naziv vrste emisije");
        JTextField nazivVrsteEmisijeValue = new JTextField(vrstaEmisije.getNazivVrsteEmisije());

        JPanel dataPanel = new JPanel(new GridLayout(1, 2));
        dataPanel.add(nazivVrsteEmisije);
        dataPanel.add(nazivVrsteEmisijeValue);
        dataPanel.setBorder(BorderFactory.createTitledBorder("Podaci o vrsti emisije"));

        add(dataPanel);

        // Create action buttons panel
        JButton saveButton = new JButton("SAVE");
        Common.addMouseListener(saveButton);
        JButton getAllButton = new JButton("DISPLAY GENRES");
        Common.addMouseListener(getAllButton);
        JButton deleteButton = new JButton("DELETE");
        Common.addMouseListener(deleteButton);
        deleteButton.setForeground(Color.RED);
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        // Define action listeners
        getAllButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(EditVrstaEmisijePanel.this, "Get all button clicked");
        });

        saveButton.addActionListener(e -> {
            if(nazivVrsteEmisijeValue.getText().isEmpty()) {
                JOptionPane.showMessageDialog(EditVrstaEmisijePanel.this, "Polja ne smiju biti prazna!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                VrstaEmisijeModel newVrstaEmisije = new VrstaEmisijeModel(vrstaEmisije.getNazivVrsteEmisije(), vrstaEmisije.getIdVrsteEmisije());
                try {
                    VrstaEmisijeModel updatedVrstaEmisije = apiResources.updateVrstaEmisijeModel(newVrstaEmisije, vrstaEmisije.getIdVrsteEmisije());
                    JOptionPane.showMessageDialog(EditVrstaEmisijePanel.this, "Vrsta emisije uspješno ažurirana!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Update frame title
                    parentFrame.setTitle(updatedVrstaEmisije.getNazivVrsteEmisije());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(EditVrstaEmisijePanel.this,
                            String.format("Greška prilikom snimanja vrste emisije: %s", ex), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                apiResources.deleteVrstaEmisije(vrstaEmisije.getIdVrsteEmisije());
                JOptionPane.showMessageDialog(EditVrstaEmisijePanel.this,
                        String.format("Vrsta emisije %suspješno obrisana!", vrstaEmisije.getNazivVrsteEmisije()), "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(EditVrstaEmisijePanel.this,
                        String.format("Greška prilikom brisanja vrste emisije: %s", ex), "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });

        buttonsPanel.add(getAllButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(deleteButton);

        add(buttonsPanel);
    }
}

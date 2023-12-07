package com.processinformationsystemsui.panel.Emisija;

import com.processinformationsystemsui.model.EmisijaModel;
import com.processinformationsystemsui.panel.Epizoda.ListaEpizoda.ListaEpizoda;
import com.processinformationsystemsui.util.Common;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmisijaPanel extends JPanel {
    private final List<JLabel> labelsData;
    private final EmisijaModel emisija;

    public EmisijaPanel (EmisijaModel emisija) {
        this.emisija = emisija;

        JButton editButton = new JButton("EDIT");
        JButton deleteButton = new JButton("DELETE");
        deleteButton.setForeground(Color.RED);

        JButton displayEpisodesButton = new JButton("DISPLAY EPISODES");

        // Add ActionListener for the Edit button
        editButton.addActionListener(e -> {
            // Add logic for editing the Emisija
            JOptionPane.showMessageDialog(EmisijaPanel.this, "Edit button clicked");
        });

        // Add ActionListener for the Delete button
        deleteButton.addActionListener(e -> {
            // Add logic for deleting the Emisija
            JOptionPane.showMessageDialog(EmisijaPanel.this, "Delete button clicked");
        });

        // Add Action Listener for Show Episodes Button
        displayEpisodesButton.addActionListener(e -> {
            // Add logic for displaying episodes
            try {
                new ListaEpizoda(emisija.getNazivEmisije(), emisija.getIdEmisije());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Common.addMouseListener(editButton);
        Common.addMouseListener(deleteButton);
        Common.addMouseListener(displayEpisodesButton);

        // Layout for the EmisijaFrame
        setLayout(new GridLayout(2, 1));

        // Add Vrsta Emisije info
        labelsData = new ArrayList<>();
        initializeVrstaEmisijeLabels();

        JPanel vrstaEmisijePanel = new JPanel();
        Common.initializeLabelsPanel(2,2, labelsData, vrstaEmisijePanel, "Vrsta Emisije", message -> JOptionPane.showMessageDialog(EmisijaPanel.this, "Vrsta emisije Clicked!"));

        labelsData.clear();

        // Add Voditelj info
        initializeVoditeljLabels();

        JPanel voditeljPanel = new JPanel();
        Common.initializeLabelsPanel(4,2, labelsData, voditeljPanel, "Voditelj", message -> JOptionPane.showMessageDialog(EmisijaPanel.this, "Voditelj Clicked!"));

        labelsData.clear();

        // Add Urednik info
        initializeUrednikLabels();

        JPanel urednikPanel = new JPanel();
        Common.initializeLabelsPanel(4,2, labelsData, urednikPanel, "Urednik", message -> JOptionPane.showMessageDialog(EmisijaPanel.this, "Urednik Clicked!"));

        labelsData.clear();

        // Add Gost info
        initializeGostLabels();

        JPanel gostPanel = new JPanel();
        Common.initializeLabelsPanel(5,2, labelsData, gostPanel, "Gost", message -> JOptionPane.showMessageDialog(EmisijaPanel.this, "Gost Clicked!"));

        JPanel listaEpizodaButtonPanel = new JPanel(new FlowLayout());
        listaEpizodaButtonPanel.setBorder(BorderFactory.createTitledBorder("Epizode"));
        listaEpizodaButtonPanel.add(displayEpisodesButton);

        // Panel for buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Dodatne opcije"));
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        add(vrstaEmisijePanel);
        add(voditeljPanel);
        add(urednikPanel);
        add(gostPanel);
        add(listaEpizodaButtonPanel);
        add(buttonsPanel);
    }

    private void initializeVrstaEmisijeLabels() {
        labelsData.add(new JLabel("ID Vrste Emisije:"));
        labelsData.add(new JLabel(emisija.getVrstaEmisije().getIdVrsteEmisije()));
        labelsData.add(new JLabel("Naziv Vrste Emisije:"));
        labelsData.add(new JLabel(emisija.getVrstaEmisije().getNazivVrsteEmisije()));
    }

    private void initializeVoditeljLabels() {
        labelsData.add(new JLabel("ID Voditelja:"));
        labelsData.add(new JLabel(emisija.getVoditelj().getIdVoditelja()));
        labelsData.add(new JLabel("Ime voditelja:"));
        labelsData.add(new JLabel(emisija.getVoditelj().getImeVoditelja()));
        labelsData.add(new JLabel("Prezime voditelja:"));
        labelsData.add(new JLabel(emisija.getVoditelj().getPrezimeVoditelja()));
        labelsData.add(new JLabel("Kontakt telefon voditelja:"));
        labelsData.add(new JLabel(emisija.getVoditelj().getKontaktTelefonVoditelja()));
    }

    private void initializeUrednikLabels() {
        labelsData.add(new JLabel("ID Urednika:"));
        labelsData.add(new JLabel(emisija.getUrednik().getIdUrednika()));
        labelsData.add(new JLabel("Ime urednika:"));
        labelsData.add(new JLabel(emisija.getUrednik().getImeUrednika()));
        labelsData.add(new JLabel("Prezime urednika:"));
        labelsData.add(new JLabel(emisija.getUrednik().getPrezimeUrednika()));
        labelsData.add(new JLabel("Kontakt telefon urednika:"));
        labelsData.add(new JLabel(emisija.getUrednik().getKontaktTelefonUrednika()));
    }

    private void initializeGostLabels() {
        labelsData.add(new JLabel("ID Gosta:"));
        labelsData.add(new JLabel(emisija.getGost().getIdGosta()));
        labelsData.add(new JLabel("Ime gosta:"));
        labelsData.add(new JLabel(emisija.getGost().getImeGosta()));
        labelsData.add(new JLabel("Prezime gosta:"));
        labelsData.add(new JLabel(emisija.getGost().getPrezimeGosta()));
        labelsData.add(new JLabel("Biografija gosta:"));
        labelsData.add(new JLabel(emisija.getGost().getBiografijaGosta()));
        labelsData.add(new JLabel("Kontakt telefon gosta:"));
        labelsData.add(new JLabel(emisija.getGost().getKontaktTelefonGosta()));
    }
}

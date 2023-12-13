package com.processinformationsystemsui.panel.Emisija;

import com.processinformationsystemsui.common.NumberInputVerifier;
import com.processinformationsystemsui.common.dialog.message.ErrorMessageDialog;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;
import com.processinformationsystemsui.model.*;
import com.processinformationsystemsui.panel.Epizoda.Create.CreateEpizodaModel;
import com.processinformationsystemsui.panel.Epizoda.ListaEpizoda.ListaEpizodaPanel;
import com.processinformationsystemsui.panel.Gost.ListaGostiju.ListaGostijuPanel;
import com.processinformationsystemsui.panel.Urednik.ListaUrednika.ListaUrednika;
import com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.ListaVoditelja;
import com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.ListaVrstaEmisija;
import com.processinformationsystemsui.common.Common;
import com.processinformationsystemsui.common.EmisijaDataChangeListener;
import com.processinformationsystemsui.common.PersonalDataLabels;
import com.processinformatuionsystemsui.api.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EmisijaPanel extends JPanel implements EmisijaDataChangeListener {
    private final List<JLabel> labelsData = new ArrayList<>();
    private final EmisijaModel emisija;

    // Vrsta Emisije section
    private final JPanel vrstaEmisijePanel = new JPanel();
    private final JLabel vrstaEmisijeLabel = new JLabel();
    private final Runnable openListaVrstaEmisija;

    // Trajanje emisije section
    private final JPanel trajanjeEmisijePanel = new JPanel();
    private final JLabel trajanjeEmisijeLabel = new JLabel();
    private final Runnable openEditTrajanjeEmisije;

    // Ocjena emisije section
    private final JPanel ocjenaEmisijePanel = new JPanel();
    private final JLabel ocjenaEmisijeLabel = new JLabel();
    private final Runnable openEditOcjenaEmisije;

    // Voditelj section
    private final List<JLabel> voditeljLabels = new ArrayList<>();
    private final PersonalDataLabels voditeljLabelsData = new PersonalDataLabels();
    private final Runnable openListaVoditelja;

    // Urednik section
    private final List<JLabel> urednikLabels = new ArrayList<>();
    private final PersonalDataLabels urednikLabelsData = new PersonalDataLabels();
    private final Runnable openListaUrednika;

    // Epizode section
    private final ListaEpizodaPanel listaEpizodaPanel;

    // Gosti section
    private final ListaGostijuPanel listaGostijuPanel;

    // API resources
    private final EmisijaApiResources emisijaApiResources = new EmisijaApiResources();
    private final VrstaEmisijeApiResources vrstaEmisijeApiResources = new VrstaEmisijeApiResources();
    private final VoditeljApiResources voditeljApiResources = new VoditeljApiResources();
    private final UrednikApiResources urednikApiResources = new UrednikApiResources();
    private final EpizodaApiResources epizodaApiResources = new EpizodaApiResources();

    // Util
    private final InformationMessageDialog informationMessageDialog = new InformationMessageDialog(EmisijaPanel.this);
    private final ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog(EmisijaPanel.this);

    public EmisijaPanel (EmisijaModel emisija, JFrame parentFrame) throws IOException {
        this.emisija = emisija;

        JButton saveButton = new JButton("SAVE");
        JButton deleteButton = new JButton("DELETE");
        deleteButton.setForeground(Color.RED);

        // Add ActionListener for the Edit button
        saveButton.addActionListener(e -> {
            // Add logic for editing the Emisija
            try {
                emisijaApiResources.updateEmisija(emisija, emisija.getIdEmisije());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            informationMessageDialog.showMessage("Nova emisija uspjesno snimljena!");
        });

        // Add ActionListener for the Delete button
        deleteButton.addActionListener(e -> {
            // Add logic for deleting the Emisija
            try {
                emisijaApiResources.deleteEmisija(emisija.getIdEmisije());

                informationMessageDialog.showMessage(String.format("Emisija: %s uspjesno obrisana!", emisija.getNazivEmisije()));

                parentFrame.dispose();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Common.addMouseListener(saveButton);
        Common.addMouseListener(deleteButton);

        // Layout for the EmisijaFrame
        setLayout(new GridLayout(2, 1));

        openListaVrstaEmisija = () -> {
            try {
                new ListaVrstaEmisija(this, true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        openEditTrajanjeEmisije = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this),
                    "Unesi novu vrijednost trajanja emisije:", trajanjeEmisijeLabel.getText());
            if (newValue != null && NumberInputVerifier.verify(newValue)) {
                trajanjeEmisijeLabel.setText(newValue);
            }
        };

        openEditOcjenaEmisije = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this),
                    "Unesi novu vrijednost ocjene emisije:", ocjenaEmisijeLabel.getText());
            if (newValue != null && NumberInputVerifier.verify(newValue)) {
                ocjenaEmisijeLabel.setText(newValue);
            }
        };

        openListaVoditelja = () -> {
            try {
                new ListaVoditelja(this, true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        openListaUrednika = () -> {
            try {
                new ListaUrednika(this, true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        // Generalni podaci panel
        JPanel generalInfoPanel = new JPanel(new GridLayout(3, 1));

        // Add Vrsta Emisije info
        vrstaEmisijeLabel.setText(emisija.getVrstaEmisije().getNazivVrsteEmisije());
        List<JLabel> vrstaEmisijeLabels = new ArrayList<>();
        vrstaEmisijeLabels.add(vrstaEmisijeLabel);

        Common.initializeLabelsPanel(2, 2, vrstaEmisijeLabels, vrstaEmisijePanel, "Vrsta emisije", openListaVrstaEmisija);

        generalInfoPanel.add(vrstaEmisijePanel);

        // Add Trajanje emisije info
        trajanjeEmisijeLabel.setText(String.valueOf(emisija.getTrajanjeEmisije()));
        List<JLabel> trajanjeEmisijeLabels = new ArrayList<>();
        trajanjeEmisijeLabels.add(trajanjeEmisijeLabel);

        Common.initializeLabelsPanel(
                2, 2, trajanjeEmisijeLabels, trajanjeEmisijePanel, "Vrijeme trajanja emisije", openEditTrajanjeEmisije);

        generalInfoPanel.add(trajanjeEmisijePanel);

        // Add ocjena emisije info
        ocjenaEmisijeLabel.setText(String.valueOf(emisija.getOcjenaEmisije()));
        List<JLabel> ocjenaEmisijeLables = new ArrayList<>();
        ocjenaEmisijeLables.add(ocjenaEmisijeLabel);

        Common.initializeLabelsPanel(
                2, 2, ocjenaEmisijeLables, ocjenaEmisijePanel, "Ocjena emisije", openEditOcjenaEmisije);

        generalInfoPanel.add(ocjenaEmisijePanel);

        // Add Voditelj info
        voditeljLabelsData.getIme().setText(emisija.getVoditelj().getImeVoditelja());
        voditeljLabelsData.getPrezime().setText(emisija.getVoditelj().getPrezimeVoditelja());
        voditeljLabelsData.getKontaktTelefon().setText(emisija.getVoditelj().getKontaktTelefonVoditelja());
        initializeVoditeljLabels();

        JPanel voditeljPanel = new JPanel();
        Common.initializeLabelsPanel(4,2, voditeljLabels, voditeljPanel, "Voditelj", openListaVoditelja);

        // Add Urednik info
        urednikLabelsData.getIme().setText(emisija.getUrednik().getImeUrednika());
        urednikLabelsData.getPrezime().setText(emisija.getUrednik().getPrezimeUrednika());
        urednikLabelsData.getKontaktTelefon().setText(emisija.getUrednik().getKontaktTelefonUrednika());
        initializeUrednikLabels();

        JPanel urednikPanel = new JPanel();
        Common.initializeLabelsPanel(4,2, urednikLabels, urednikPanel, "Urednik", openListaUrednika);

        labelsData.clear();

        listaGostijuPanel = new ListaGostijuPanel(emisija.getIdEmisije(), this, parentFrame);
        listaGostijuPanel.setBorder(BorderFactory.createTitledBorder("Gosti"));

        listaEpizodaPanel = new ListaEpizodaPanel(emisija.getIdEmisije(), this, parentFrame);
        listaEpizodaPanel.setBorder(BorderFactory.createTitledBorder("Epizode"));

        // Panel for buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Opcije"));
        buttonsPanel.add(saveButton);
        buttonsPanel.add(deleteButton);

        add(generalInfoPanel);
        add(listaEpizodaPanel);
        add(voditeljPanel);
        add(urednikPanel);
        add(listaGostijuPanel);
        add(buttonsPanel);
    }

    private void initializeVoditeljLabels() {
        voditeljLabels.add(new JLabel("Ime voditelja:"));
        voditeljLabels.add(voditeljLabelsData.getIme());
        voditeljLabels.add(new JLabel("Prezime voditelja:"));
        voditeljLabels.add(voditeljLabelsData.getPrezime());
        voditeljLabels.add(new JLabel("Kontakt telefon voditelja:"));
        voditeljLabels.add(voditeljLabelsData.getKontaktTelefon());
    }

    private void initializeUrednikLabels() {
        urednikLabels.add(new JLabel("Ime urednika:"));
        urednikLabels.add(urednikLabelsData.getIme());
        urednikLabels.add(new JLabel("Prezime urednika:"));
        urednikLabels.add(urednikLabelsData.getPrezime());
        urednikLabels.add(new JLabel("Kontakt telefon urednika:"));
        urednikLabels.add(urednikLabelsData.getKontaktTelefon());
    }

    @Override
    public void onVrstaEmisijeSelected(Object data) {
        try {
            VrstaEmisijeModel newVrstaEmisije = vrstaEmisijeApiResources.getVrstaEmisijeById(data.toString());
            emisija.setVrstaEmisije(newVrstaEmisije);

            vrstaEmisijeLabel.setText(newVrstaEmisije.getNazivVrsteEmisije());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onGostSelected(GostModel data) {
        try {
            Set<GostModel> gosti = emisija.getGosti();
            gosti.add(data);
            emisija.setGosti(gosti);

            emisijaApiResources.updateEmisija(emisija, emisija.getIdEmisije());

            listaGostijuPanel.updateList();
        } catch (IOException e) {
            errorMessageDialog.showMessage(String.format("Greska prilikom dodavanja novog gosta: %s", e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onVoditeljSelected(Object data) {
        try {
            VoditeljModel newVoditelj = voditeljApiResources.getVoditeljById(data.toString());
            emisija.setVoditelj(newVoditelj);

            voditeljLabelsData.getIme().setText(newVoditelj.getImeVoditelja());
            voditeljLabelsData.getPrezime().setText(newVoditelj.getPrezimeVoditelja());
            voditeljLabelsData.getKontaktTelefon().setText(newVoditelj.getKontaktTelefonVoditelja());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUrednikSelected(Object data) {
        try {
            UrednikModel newUrednik = urednikApiResources.getUrednikById(data.toString());
            emisija.setUrednik(newUrednik);

            urednikLabelsData.getIme().setText(newUrednik.getImeUrednika());
            urednikLabelsData.getPrezime().setText(newUrednik.getPrezimeUrednika());
            urednikLabelsData.getKontaktTelefon().setText(newUrednik.getKontaktTelefonUrednika());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEpizodaAdded(CreateEpizodaModel data) {
        try {
            data.setIdEmisije(emisija.getIdEmisije());
            epizodaApiResources.createEpizoda(data);
            listaEpizodaPanel.updateList();
        } catch (IOException e) {
            errorMessageDialog.showMessage(String.format("Greska prilikom kreiranja epizode! %s", e.getMessage()));
            throw new RuntimeException(e);
        }
    }
}

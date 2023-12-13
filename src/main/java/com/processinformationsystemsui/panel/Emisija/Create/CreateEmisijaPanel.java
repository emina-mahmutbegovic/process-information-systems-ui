package com.processinformationsystemsui.panel.Emisija.Create;

import com.processinformationsystemsui.common.Common;
import com.processinformationsystemsui.common.NumberInputVerifier;
import com.processinformationsystemsui.common.PersonalDataLabels;
import com.processinformationsystemsui.common.dialog.message.ErrorMessageDialog;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;
import com.processinformationsystemsui.common.dialog.message.ValidationErrorMessageDialog;
import com.processinformationsystemsui.model.*;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Epizoda.Create.CreateEpizodaModel;
import com.processinformationsystemsui.panel.Epizoda.ListaEpizoda.ListaEpizodaPanel;
import com.processinformationsystemsui.panel.Gost.ListaGostiju.ListaGostijuPanel;
import com.processinformationsystemsui.panel.Urednik.ListaUrednika.ListaUrednika;
import com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.ListaVoditelja;
import com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.ListaVrstaEmisija;
import com.processinformatuionsystemsui.api.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CreateEmisijaPanel extends JPanel implements EmisijaDataChangeListener {
    private final List<JLabel> labelsData = new ArrayList<>();
    protected EmisijaModel emisija = new EmisijaModel();

    // Vrsta Emisije section
    private final JPanel vrstaEmisijePanel = new JPanel();
    protected final JLabel vrstaEmisijeLabel = new JLabel();
    private final Runnable openListaVrstaEmisija;
    protected VrstaEmisijeModel selectedVrstaEmisije;

    // Trajanje emisije section
    private final JPanel trajanjeEmisijePanel = new JPanel();
    protected final JLabel trajanjeEmisije = new JLabel();
    private final Runnable openEditTrajanjeEmisije;

    // Ocjena emisije section
    private final JPanel ocjenaEmisijePanel = new JPanel();
    protected final JLabel ocjenaEmisije = new JLabel();
    private final Runnable openEditOcjenaEmisije;

    // Voditelj section
    private final List<JLabel> voditeljLabels = new ArrayList<>();
    protected final PersonalDataLabels voditeljLabelsData = new PersonalDataLabels();
    private final Runnable openListaVoditelja;
    protected VoditeljModel selectedVoditelj;

    // Urednik section
    private final List<JLabel> urednikLabels = new ArrayList<>();
    protected final PersonalDataLabels urednikLabelsData = new PersonalDataLabels();
    private final Runnable openListaUrednika;
    protected UrednikModel selectedUrednik;

    // Epizode section
    private final ListaEpizodaPanel listaEpizodaPanel;

    // Gosti section
    private final ListaGostijuPanel listaGostijuPanel;

    // Opcije section
    protected final JPanel buttonsPanel;

    // API resources
    protected final EmisijaApiResources emisijaApiResources = new EmisijaApiResources();
    private final VrstaEmisijeApiResources vrstaEmisijeApiResources = new VrstaEmisijeApiResources();
    private final VoditeljApiResources voditeljApiResources = new VoditeljApiResources();
    private final UrednikApiResources urednikApiResources = new UrednikApiResources();
    private final EpizodaApiResources epizodaApiResources = new EpizodaApiResources();

    // Util
    private final InformationMessageDialog informationMessageDialog = new InformationMessageDialog(CreateEmisijaPanel.this);
    private final ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog(CreateEmisijaPanel.this);
    private final ValidationErrorMessageDialog validationErrorMessageDialog = new ValidationErrorMessageDialog(CreateEmisijaPanel.this);

    public CreateEmisijaPanel(EmisijaModel emisija) throws IOException {
        this.emisija = emisija;

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
                    "Unesi novu vrijednost trajanja emisije:", trajanjeEmisije.getText());
            if (newValue != null && NumberInputVerifier.verify(newValue)) {
                trajanjeEmisije.setText(newValue);
            }
        };

        openEditOcjenaEmisije = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this),
                    "Unesi novu vrijednost ocjene emisije:", ocjenaEmisije.getText());
            if (newValue != null && NumberInputVerifier.verify(newValue)) {
                ocjenaEmisije.setText(newValue);
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
        setVrstaEmisijeInfo();
        List<JLabel> vrstaEmisijeLabels = new ArrayList<>();
        vrstaEmisijeLabels.add(vrstaEmisijeLabel);

        Common.initializeLabelsPanel(2, 2, vrstaEmisijeLabels, vrstaEmisijePanel, "Vrsta emisije", openListaVrstaEmisija);

        generalInfoPanel.add(vrstaEmisijePanel);

        // Add Trajanje emisije info
        setTrajanjeEmisijeInfo();
        List<JLabel> trajanjeEmisijeLabels = new ArrayList<>();
        trajanjeEmisijeLabels.add(trajanjeEmisije);

        Common.initializeLabelsPanel(
                2, 2, trajanjeEmisijeLabels, trajanjeEmisijePanel, "Vrijeme trajanja emisije", openEditTrajanjeEmisije);

        generalInfoPanel.add(trajanjeEmisijePanel);

        // Add ocjena emisije info
        setOcjenaEmisijeInfo();
        List<JLabel> ocjenaEmisijeLables = new ArrayList<>();
        ocjenaEmisijeLables.add(ocjenaEmisije);

        Common.initializeLabelsPanel(
                2, 2, ocjenaEmisijeLables, ocjenaEmisijePanel, "Ocjena emisije", openEditOcjenaEmisije);

        generalInfoPanel.add(ocjenaEmisijePanel);

        // Add Voditelj info
        setVoditeljInfo();
        initializeVoditeljLabels();

        JPanel voditeljPanel = new JPanel();
        Common.initializeLabelsPanel(4, 2, voditeljLabels, voditeljPanel, "Voditelj", openListaVoditelja);

        // Add Urednik info
        setUrednikInfo();
        initializeUrednikLabels();

        JPanel urednikPanel = new JPanel();
        Common.initializeLabelsPanel(4, 2, urednikLabels, urednikPanel, "Urednik", openListaUrednika);

        labelsData.clear();

        listaGostijuPanel = new ListaGostijuPanel(emisija.getIdEmisije(), this, (JFrame) SwingUtilities.getWindowAncestor(this));
        listaGostijuPanel.setBorder(BorderFactory.createTitledBorder("Gosti"));

        listaEpizodaPanel = new ListaEpizodaPanel(emisija.getIdEmisije(), this, (JFrame) SwingUtilities.getWindowAncestor(this));
        listaEpizodaPanel.setBorder(BorderFactory.createTitledBorder("Epizode"));

        // Panel for buttons
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Opcije"));

        addSaveButton();

        add(generalInfoPanel);
        add(listaEpizodaPanel);
        add(voditeljPanel);
        add(urednikPanel);
        add(listaGostijuPanel);
        add(buttonsPanel);
    }

    private void addSaveButton() {
        JButton saveButton = new JButton("SAVE");

        // Add ActionListener for the Edit button
        saveButton.addActionListener(e -> {
            // Add logic for editing the Emisija
            try {
                String trajanjeEmisijeValue = trajanjeEmisije.getText().split("\\s+")[0];

                if(selectedVrstaEmisije != null &&
                !ocjenaEmisije.getText().isBlank() &&
                !trajanjeEmisijeValue.isBlank() &&
                selectedUrednik != null &&
                selectedVoditelj != null) {
                    emisija.setVrstaEmisije(selectedVrstaEmisije);
                    emisija.setOcjenaEmisije(Integer.parseInt(ocjenaEmisije.getText()));
                    emisija.setTrajanjeEmisije(Integer.parseInt(trajanjeEmisijeValue));
                    emisija.setUrednik(selectedUrednik);
                    emisija.setVoditelj(selectedVoditelj);

                    onSaveButtonClicked();

                    informationMessageDialog.showMessage("Nova emisija uspjesno snimljena!");
                } else {
                    validationErrorMessageDialog.showMessage("Polja ne smiju biti prazna!");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Common.addMouseListener(saveButton);

        buttonsPanel.add(saveButton);
    }

    protected void setVoditeljInfo() {}

    protected void setUrednikInfo() {}

    protected void setVrstaEmisijeInfo() {}

    protected void setTrajanjeEmisijeInfo() {}

    protected void setOcjenaEmisijeInfo() {}

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

    protected void onSaveButtonClicked() throws IOException {
        CreateEmisijaModel newEmisija = new CreateEmisijaModel(
                emisija.getNazivEmisije(),
                emisija.getOpisEmisije(),
                emisija.getTrajanjeEmisije(),
                emisija.getOcjenaEmisije(),
                emisija.getVrstaEmisije(),
                emisija.getVoditelj(),
                emisija.getUrednik(),
                emisija.getGosti()
        );

        emisijaApiResources.createEmisija(newEmisija);
    }

    @Override
    public void onVrstaEmisijeSelected(Object data) {
        try {
            selectedVrstaEmisije = vrstaEmisijeApiResources.getVrstaEmisijeById(data.toString());

            vrstaEmisijeLabel.setText(selectedVrstaEmisije.getNazivVrsteEmisije());
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
            selectedVoditelj = voditeljApiResources.getVoditeljById(data.toString());

            voditeljLabelsData.getIme().setText(selectedVoditelj.getImeVoditelja());
            voditeljLabelsData.getPrezime().setText(selectedVoditelj.getPrezimeVoditelja());
            voditeljLabelsData.getKontaktTelefon().setText(selectedVoditelj.getKontaktTelefonVoditelja());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUrednikSelected(Object data) {
        try {
            selectedUrednik = urednikApiResources.getUrednikById(data.toString());

            urednikLabelsData.getIme().setText(selectedUrednik.getImeUrednika());
            urednikLabelsData.getPrezime().setText(selectedUrednik.getPrezimeUrednika());
            urednikLabelsData.getKontaktTelefon().setText(selectedUrednik.getKontaktTelefonUrednika());
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

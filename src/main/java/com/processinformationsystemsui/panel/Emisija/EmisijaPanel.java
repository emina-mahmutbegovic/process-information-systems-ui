package com.processinformationsystemsui.panel.Emisija;

import com.processinformationsystemsui.common.*;
import com.processinformationsystemsui.common.button.SaveButton;
import com.processinformationsystemsui.common.dialog.message.ErrorMessageDialog;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;
import com.processinformationsystemsui.common.dialog.message.ValidationErrorMessageDialog;
import com.processinformationsystemsui.model.*;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Epizoda.Create.CreateEpizodaModel;
import com.processinformationsystemsui.panel.Urednik.ListaUrednika.Select.ListaUrednikaSelect;
import com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.Select.ListaVoditeljaSelect;
import com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.Select.ListaVrstaEmisijaSelect;
import com.processinformatuionsystemsui.api.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmisijaPanel extends JPanel implements EmisijaDataChangeListener {
    protected EmisijaModel emisija;

    // Naziv emisije section
    protected final JLabel nazivEmisije = new JLabel();

    // Opis emisije section
    protected final JTextArea opisEmisije = new JTextArea();

    protected final JLabel vrstaEmisijeLabel = new JLabel();
    protected VrstaEmisijeModel selectedVrstaEmisije;

    protected final JLabel trajanjeEmisije = new JLabel();

    protected final JLabel ocjenaEmisije = new JLabel();

    // Voditelj section
    private final List<JLabel> voditeljLabels = new ArrayList<>();
    protected final PersonalDataLabels voditeljLabelsData = new PersonalDataLabels();
    protected VoditeljModel selectedVoditelj;

    // Urednik section
    private final List<JLabel> urednikLabels = new ArrayList<>();
    protected final PersonalDataLabels urednikLabelsData = new PersonalDataLabels();
    protected UrednikModel selectedUrednik;

    // Opcije section
    protected final JPanel buttonsPanel;

    // API resources
    protected final EmisijaApiResources emisijaApiResources = new EmisijaApiResources();
    protected final VrstaEmisijeApiResources vrstaEmisijeApiResources = new VrstaEmisijeApiResources();
    protected final VoditeljApiResources voditeljApiResources = new VoditeljApiResources();
    protected final UrednikApiResources urednikApiResources = new UrednikApiResources();
    protected final EpizodaApiResources epizodaApiResources = new EpizodaApiResources();

    // Util
    protected final InformationMessageDialog informationMessageDialog = new InformationMessageDialog(EmisijaPanel.this);
    protected final ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog(EmisijaPanel.this);
    protected final ValidationErrorMessageDialog validationErrorMessageDialog = new ValidationErrorMessageDialog(EmisijaPanel.this);

    public EmisijaPanel(EmisijaModel emisija, Dimensions dimensions) throws IOException {
        this.emisija = emisija;

        // Layout for the EmisijaFrame
        setLayout(new GridLayout(dimensions.x(), dimensions.y()));

        Runnable openEditNazivEmisije = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this),
                    "Unesi novi naziv emisije:", nazivEmisije.getText());
            if (newValue != null && !newValue.isBlank()) {
                nazivEmisije.setText(newValue);
            }
        };

        Runnable openEditOpisEmisije = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this),
                    "Unesi novi opis emisije:", opisEmisije.getText());
            if (newValue != null && !newValue.isBlank()) {
                opisEmisije.setText(newValue);
            }
        };


        Runnable openListaVrstaEmisija = () -> {
            try {
                new ListaVrstaEmisijaSelect(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable openEditTrajanjeEmisije = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this),
                    "Unesi novu vrijednost trajanja emisije (u minutama):", trajanjeEmisije.getText());
            if (newValue != null && NumberInputVerifier.verify(newValue)) {
                trajanjeEmisije.setText(newValue);
            }
        };

        Runnable openEditOcjenaEmisije = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this),
                    "Unesi novu vrijednost ocjene emisije:", ocjenaEmisije.getText());
            if (newValue != null && NumberInputVerifier.verify(newValue)) {
                ocjenaEmisije.setText(newValue);
            }
        };

        Runnable openListaVoditelja = () -> {
            try {
                new ListaVoditeljaSelect(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable openListaUrednika = () -> {
            try {
                new ListaUrednikaSelect(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        // Generalni podaci panel
        JPanel generalInfoPanel = new JPanel(new GridLayout(2, 1));

        // Add Naziv emisije info
        setNazivEmisijeInfo();
        List<JLabel> nazivEmisijeLabels = new ArrayList<>();
        nazivEmisijeLabels.add(nazivEmisije);

        // Naziv emisije section
        JPanel nazivEmisijePanel = new JPanel();
        Common.initializeLabelsPanel(DimensionsEnum.twoTimesTwo.getDimensions(), nazivEmisijeLabels, nazivEmisijePanel, "Naziv emisije", openEditNazivEmisije);

        // Add Vrsta Emisije info
        setVrstaEmisijeInfo();
        List<JLabel> vrstaEmisijeLabels = new ArrayList<>();
        vrstaEmisijeLabels.add(vrstaEmisijeLabel);

        // Vrsta Emisije section
        JPanel vrstaEmisijePanel = new JPanel();
        Common.initializeLabelsPanel(DimensionsEnum.twoTimesTwo.getDimensions(), vrstaEmisijeLabels, vrstaEmisijePanel, "Vrsta emisije", openListaVrstaEmisija);

        generalInfoPanel.add(nazivEmisijePanel);
        generalInfoPanel.add(vrstaEmisijePanel);

        // Add Opis emisije info
        setOpisEmisijeInfo();

        // Opis emisije section
        JPanel opisEmisijePanel = new JPanel();
        Common.initializeTextAreaPanel(opisEmisije, opisEmisijePanel, "Opis emisije", openEditOpisEmisije);

        // Add metrics panel
        JPanel metricsPanel = new JPanel(new GridLayout(2, 1));

        // Add Trajanje emisije info
        setTrajanjeEmisijeInfo();
        List<JLabel> trajanjeEmisijeLabels = new ArrayList<>();
        trajanjeEmisijeLabels.add(trajanjeEmisije);

        // Trajanje emisije section
        JPanel trajanjeEmisijePanel = new JPanel();
        Common.initializeLabelsPanel(
                DimensionsEnum.twoTimesTwo.getDimensions(), trajanjeEmisijeLabels, trajanjeEmisijePanel, "Vrijeme trajanja emisije", openEditTrajanjeEmisije);

        // Add ocjena emisije info
        setOcjenaEmisijeInfo();
        List<JLabel> ocjenaEmisijeLables = new ArrayList<>();
        ocjenaEmisijeLables.add(ocjenaEmisije);

        // Ocjena emisije section
        JPanel ocjenaEmisijePanel = new JPanel();
        Common.initializeLabelsPanel(
                DimensionsEnum.twoTimesTwo.getDimensions(), ocjenaEmisijeLables, ocjenaEmisijePanel, "Ocjena emisije", openEditOcjenaEmisije);

        metricsPanel.add(trajanjeEmisijePanel);
        metricsPanel.add(ocjenaEmisijePanel);

        // Add Voditelj info
        setVoditeljInfo();
        initializeVoditeljLabels();

        JPanel voditeljPanel = new JPanel();
        Common.initializeLabelsPanel(DimensionsEnum.fourTimesTwo.getDimensions(), voditeljLabels, voditeljPanel, "Voditelj", openListaVoditelja);

        // Add Urednik info
        setUrednikInfo();
        initializeUrednikLabels();

        JPanel urednikPanel = new JPanel();
        Common.initializeLabelsPanel(DimensionsEnum.fourTimesTwo.getDimensions(), urednikLabels, urednikPanel, "Urednik", openListaUrednika);

        // Panel for buttons
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Opcije"));

        addSaveButton();

        add(generalInfoPanel);
        add(opisEmisijePanel);
        add(metricsPanel);
        add(voditeljPanel);
        add(urednikPanel);
    }

    protected void addButtonsPanel() {
        add(buttonsPanel);
    }

    private void addSaveButton() {
        Runnable saveAction = () -> {
            // Add logic for creating or editing the Emisija
            try {
                if(selectedVrstaEmisije != null &&
                        !nazivEmisije.getText().isBlank() &&
                        !opisEmisije.getText().isBlank() &&
                        !ocjenaEmisije.getText().isBlank() &&
                        !trajanjeEmisije.getText().isBlank() &&
                        selectedUrednik != null &&
                        selectedVoditelj != null) {
                    emisija.setNazivEmisije(nazivEmisije.getText());
                    emisija.setOpisEmisije(opisEmisije.getText());
                    emisija.setVrstaEmisije(selectedVrstaEmisije);
                    emisija.setOcjenaEmisije(Integer.parseInt(ocjenaEmisije.getText()));
                    emisija.setTrajanjeEmisije(Integer.parseInt(trajanjeEmisije.getText()));
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
        };

        JButton saveButton = new SaveButton(saveAction);

        buttonsPanel.add(saveButton);
    }

    protected void setNazivEmisijeInfo() {}

    protected void setOpisEmisijeInfo() {}

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
        // Override method
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
    public void onGostSelected(GostModel data) {}

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
    public void onEpizodaAdded(CreateEpizodaModel data) {}

    @Override
    public void onEpizodaDeleted() throws IOException {}

    @Override
    public void onGostDeleted() throws IOException {

    }
}

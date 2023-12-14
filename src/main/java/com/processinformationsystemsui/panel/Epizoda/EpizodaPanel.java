package com.processinformationsystemsui.panel.Epizoda;

import com.processinformationsystemsui.common.Common;
import com.processinformationsystemsui.common.DimensionsEnum;
import com.processinformationsystemsui.common.button.DeleteButton;
import com.processinformationsystemsui.common.button.SaveButton;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Epizoda.Data.EpizodaDataChangeListener;
import com.processinformationsystemsui.common.NumberInputVerifier;
import com.processinformationsystemsui.common.dialog.message.ErrorMessageDialog;
import com.processinformationsystemsui.common.dialog.message.InformationMessageDialog;
import com.processinformationsystemsui.common.dialog.message.ValidationErrorMessageDialog;
import com.processinformationsystemsui.model.EpizodaModel;
import com.processinformationsystemsui.panel.TerminEmitovanja.Create.CreateTerminEmitovanjaModel;
import com.processinformationsystemsui.panel.TerminEmitovanja.ListaTerminaEmitovanja.ListaTerminaEmitovanjaPanel;
import com.processinformatuionsystemsui.api.EpizodaApiResources;
import com.processinformatuionsystemsui.api.TerminEmitovanjaApiResources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EpizodaPanel extends JPanel implements EpizodaDataChangeListener {
    protected final EpizodaModel epizoda;

    // UI
    private final JPanel actionButtonsPanel = new JPanel(new FlowLayout());
    private final ListaTerminaEmitovanjaPanel listaTerminaEmitovanjaPanel;

    // API
    private final EpizodaApiResources epizodaApiResources = new EpizodaApiResources();
    private final TerminEmitovanjaApiResources terminEmitovanjaApiResources = new TerminEmitovanjaApiResources();

    // Fields
    private final JLabel nazivEpizode;
    private final JTextArea opisEpizode;
    private final JLabel brojEpizode;
    private final JLabel brojSezone;

    // Util
    private final InformationMessageDialog informationMessageDialog = new InformationMessageDialog(EpizodaPanel.this);
    private final ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog(EpizodaPanel.this);
    private final ValidationErrorMessageDialog validationErrorMessageDialog = new ValidationErrorMessageDialog(EpizodaPanel.this);

    // Data exchange
    private final EmisijaDataChangeListener emisijaDataChangeListener;

    public EpizodaPanel(EpizodaModel epizoda, EmisijaDataChangeListener emisijaDataChangeListener) throws IOException {
        this.epizoda = epizoda;
        this.emisijaDataChangeListener = emisijaDataChangeListener;

        setLayout(new GridLayout(2, 2));

        // Add episode name
        nazivEpizode = new JLabel(epizoda.getNazivEpizode());
        JPanel nazivEpizodePanel = new JPanel();

        List<JLabel> nazivEpizodeLabels = new ArrayList<>();
        nazivEpizodeLabels.add(nazivEpizode);

        Runnable openEditNazivEpizode = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(EpizodaPanel.this),
                    "Unesi novu vrijednost naziva epizode:", nazivEpizode.getText());
            if (!newValue.isBlank()) {
                nazivEpizode.setText(newValue);
            } else {
                validationErrorMessageDialog.showMessage("Polja ne smiju biti prazna!");
            }
        };

        Common.initializeLabelsPanel(
                DimensionsEnum.twoTimesTwo.getDimensions(), nazivEpizodeLabels, nazivEpizodePanel, "Naziv epizode", openEditNazivEpizode);

        // Add description
        opisEpizode = new JTextArea(epizoda.getOpisEpizode());
        JPanel opisEpizodePanel = new JPanel();

        Runnable openEditOpisEpizode = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(EpizodaPanel.this),
                    "Unesi novu vrijednost opisa epizode:", opisEpizode.getText());
            if (!newValue.isBlank()) {
                opisEpizode.setText(newValue);
            } else {
                validationErrorMessageDialog.showMessage("Polja ne smiju biti prazna!");
            }
        };

        Common.initializeTextAreaPanel(opisEpizode, opisEpizodePanel, "Opis epizode" , openEditOpisEpizode);

        // Add episode name and description
        JPanel nazivIOpisEpizodePanel = new JPanel(new GridLayout(2, 1));
        nazivIOpisEpizodePanel.add(nazivEpizodePanel);
        nazivIOpisEpizodePanel.add(opisEpizodePanel);

        add(nazivIOpisEpizodePanel);

        // Add episode number and season number
        JPanel episodeAndSeasonPanel = new JPanel();
        episodeAndSeasonPanel.setLayout(new GridLayout(2, 1));

        // Add epizode number
        brojEpizode = new JLabel(String.valueOf(epizoda.getBrojEpizode()));
        JPanel brojEpizodePanel = new JPanel();

        List<JLabel> brojEpizodeLabels = new ArrayList<>();
        brojEpizodeLabels.add(brojEpizode);

        Runnable openEditBrojEpizode = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(EpizodaPanel.this),
                    "Unesi novu vrijednost epizode:", brojEpizode.getText());
            if (newValue != null && NumberInputVerifier.verify(newValue)) {
                brojEpizode.setText(newValue);
            }
        };

        Common.initializeLabelsPanel(DimensionsEnum.twoTimesTwo.getDimensions(), brojEpizodeLabels, brojEpizodePanel, "Broj epizode", openEditBrojEpizode);

        // Add sezona number
        brojSezone = new JLabel(String.valueOf(epizoda.getBrojSezone()));
        JPanel brojSezonePanel = new JPanel();

        List<JLabel> brojSezoneLabels = new ArrayList<>();
        brojSezoneLabels.add(brojSezone);

        Runnable openEditBrojSezone = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(EpizodaPanel.this),
                    "Unesi novu vrijednost sezone:", brojSezone.getText());
            if (newValue != null && NumberInputVerifier.verify(newValue)) {
                brojSezone.setText(newValue);
            }
        };

        Common.initializeLabelsPanel(DimensionsEnum.twoTimesTwo.getDimensions(), brojSezoneLabels, brojSezonePanel, "Broj sezone", openEditBrojSezone);

        episodeAndSeasonPanel.add(brojEpizodePanel);
        episodeAndSeasonPanel.add(brojSezonePanel);

        add(episodeAndSeasonPanel);

        listaTerminaEmitovanjaPanel = new ListaTerminaEmitovanjaPanel(
                epizoda, this, (JFrame) SwingUtilities.getWindowAncestor(EpizodaPanel.this));

        listaTerminaEmitovanjaPanel.setBorder(BorderFactory.createTitledBorder("Termini emitovanja"));
        add(listaTerminaEmitovanjaPanel);

        initializeActionButtonsPanel();
        add(actionButtonsPanel);
    }

    void initializeActionButtonsPanel() {
        Runnable saveButtonAction = () -> {
            try {
                epizoda.setNazivEpizode(nazivEpizode.getText());
                epizoda.setOpisEpizode(opisEpizode.getText());
                epizoda.setBrojEpizode(Integer.parseInt(brojEpizode.getText()));
                epizoda.setBrojSezone(Integer.parseInt(brojSezone.getText()));

                epizodaApiResources.updateEpizoda(epizoda, epizoda.getIdEpizode());
                informationMessageDialog.showMessage(String.format("Epizoda %s je uspješno snimljena!", epizoda.getNazivEpizode()));
            } catch (IOException ex) {
                errorMessageDialog.showMessage(String.format("Greška prilikom snimanja epizode: %s", ex));
                throw new RuntimeException(ex);
            }
        };

        Runnable deleteButtonAction = () -> {
            try {
                epizodaApiResources.deleteEpizoda(epizoda.getIdEpizode());

                // Emit deleted event
                emisijaDataChangeListener.onEpizodaDeleted();

                informationMessageDialog.showMessage(String.format("Epizoda %s je uspješno obrisana sa emisije: %s", epizoda.getNazivEpizode(), epizoda.getEmisija().getNazivEmisije()));
                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);

                parent.dispose();
            } catch (IOException ex) {
                errorMessageDialog.showMessage(String.format("Greška prilikom brisanja epizode: %s", ex));
                throw new RuntimeException(ex);
            }
        };

        JButton saveButton = new SaveButton(saveButtonAction);
        JButton deleteButton = new DeleteButton(deleteButtonAction);

        actionButtonsPanel.setBorder(BorderFactory.createTitledBorder("Opcije"));
        actionButtonsPanel.add(saveButton);
        actionButtonsPanel.add(deleteButton);
    }

    @Override
    public void onTerminEmitovanjaAdded(CreateTerminEmitovanjaModel data) {
        try {
            data.setIdEpizode(epizoda.getIdEpizode());
            terminEmitovanjaApiResources.createTerminEmitovanja(data);
            listaTerminaEmitovanjaPanel.updateList();
        } catch (IOException e) {
            errorMessageDialog.showMessage(String.format("Greska prilikom kreiranja epizode! %s", e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTerminEmitovanjaDeleted() throws IOException {
        listaTerminaEmitovanjaPanel.updateList();
    }
}

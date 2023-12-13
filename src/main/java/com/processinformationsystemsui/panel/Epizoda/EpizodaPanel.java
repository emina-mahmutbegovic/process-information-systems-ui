package com.processinformationsystemsui.panel.Epizoda;

import com.processinformationsystemsui.common.Common;
import com.processinformationsystemsui.common.EpizodaDataChangeListener;
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
    private final JPanel deleteButtonPanel = new JPanel(new FlowLayout());
    private final ListaTerminaEmitovanjaPanel listaTerminaEmitovanjaPanel;

    // API
    private final EpizodaApiResources epizodaApiResources = new EpizodaApiResources();
    private final TerminEmitovanjaApiResources terminEmitovanjaApiResources = new TerminEmitovanjaApiResources();

    // Util
    private final InformationMessageDialog informationMessageDialog = new InformationMessageDialog(EpizodaPanel.this);
    private final ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog(EpizodaPanel.this);
    private final ValidationErrorMessageDialog validationErrorMessageDialog = new ValidationErrorMessageDialog(EpizodaPanel.this);

    // Runnable
    private final Runnable openEditNazivEpizode;
    private final Runnable openEditOpisEpizode;

    public EpizodaPanel(EpizodaModel epizoda) throws IOException {
        this.epizoda = epizoda;

        setLayout(new GridLayout(2, 2));

        // Add episode name
        JLabel nazivEpizode = new JLabel(epizoda.getNazivEpizode());
        JPanel nazivEpizodePanel = new JPanel();

        List<JLabel> nazivEpizodeLabels = new ArrayList<>();
        nazivEpizodeLabels.add(nazivEpizode);

        openEditNazivEpizode = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this),
                    "Unesi novu vrijednost naziva epizode:", nazivEpizode.getText());
            if (!newValue.isBlank()) {
                nazivEpizode.setText(newValue);
            } else {
                validationErrorMessageDialog.showMessage("Polja ne smiju biti prazna!");
            }
        };

        Common.initializeLabelsPanel(
                2, 2, nazivEpizodeLabels, nazivEpizodePanel, "Naziv epizode", openEditNazivEpizode);

        // Add description
        JTextArea opisEpizodeValue = new JTextArea(epizoda.getOpisEpizode());
        JPanel opisEpizodePanel = new JPanel();

        openEditOpisEpizode = () -> {
            String newValue = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this),
                    "Unesi novu vrijednost opisa epizode:", opisEpizodeValue.getText());
            if (!newValue.isBlank()) {
                opisEpizodeValue.setText(newValue);
            } else {
                validationErrorMessageDialog.showMessage("Polja ne smiju biti prazna!");
            }
        };

        Common.initializeTextAreaPanel(opisEpizodeValue, opisEpizodePanel, "Opis epizode" , openEditOpisEpizode);

        // Add episode name and description
        JPanel nazivIOpisEpizodePanel = new JPanel(new GridLayout(2, 1));
        nazivIOpisEpizodePanel.add(nazivEpizodePanel);
        nazivIOpisEpizodePanel.add(opisEpizodePanel);

        add(nazivIOpisEpizodePanel);

        // Add episode number and season number
        JPanel episodeAndSeasonPanel = new JPanel();
        episodeAndSeasonPanel.setLayout(new GridLayout(2, 1));

        // Add epizode number
        JLabel brojEpizodeValue = new JLabel(String.valueOf(epizoda.getBrojEpizode()));
        JPanel brojEpizodePanel = new JPanel();
        brojEpizodePanel.setBorder(BorderFactory.createTitledBorder("Broj epizode"));
        brojEpizodePanel.add(brojEpizodeValue);

        // Add sezona number
        JLabel brojSezoneValue = new JLabel(String.valueOf(epizoda.getBrojSezone()));
        JPanel brojSezonePanel = new JPanel();
        brojSezonePanel.setBorder(BorderFactory.createTitledBorder("Broj sezone"));
        brojSezonePanel.add(brojSezoneValue);

        episodeAndSeasonPanel.add(brojEpizodePanel);
        episodeAndSeasonPanel.add(brojSezonePanel);

        add(episodeAndSeasonPanel);

        listaTerminaEmitovanjaPanel = new ListaTerminaEmitovanjaPanel(
                epizoda, this, (JFrame) SwingUtilities.getWindowAncestor(this));

        listaTerminaEmitovanjaPanel.setBorder(BorderFactory.createTitledBorder("Termini emitovanja"));
        add(listaTerminaEmitovanjaPanel);

        initializeDeleteButtonPanel();
        add(deleteButtonPanel);
    }

    void initializeDeleteButtonPanel() {
        JButton deleteButton = new JButton("DELETE");
        deleteButton.setForeground(Color.red);

        deleteButtonPanel.setBorder(BorderFactory.createTitledBorder("Opcije"));
        deleteButtonPanel.add(deleteButton);

        Common.addMouseListener(deleteButton);
        deleteButton.addActionListener(e-> {
            try {
                epizodaApiResources.deleteEpizoda(epizoda.getIdEpizode());
                informationMessageDialog.showMessage(String.format("Epizoda %s je uspješno obrisana sa emisije: %s", epizoda.getNazivEpizode(), epizoda.getEmisija().getNazivEmisije()));
                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);

                parent.dispose();
            } catch (IOException ex) {
                errorMessageDialog.showMessage(String.format("Greška prilikom brisanja epizode: %s", ex));
                throw new RuntimeException(ex);
            }
        });
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
}

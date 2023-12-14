package com.processinformationsystemsui.panel.Emisija.Edit;

import com.processinformationsystemsui.common.DimensionsEnum;
import com.processinformationsystemsui.common.button.DeleteButton;
import com.processinformationsystemsui.model.*;
import com.processinformationsystemsui.panel.Emisija.EmisijaPanel;
import com.processinformationsystemsui.panel.Emisija.ListaEmisija.Data.ListaEmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Epizoda.Create.CreateEpizodaModel;
import com.processinformationsystemsui.panel.Epizoda.ListaEpizoda.ListaEpizodaPanel;
import com.processinformationsystemsui.panel.Gost.ListaGostiju.ListaGostijuPanel;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class EditEmisijaPanel extends EmisijaPanel {
    // Epizode section
    private final ListaEpizodaPanel listaEpizodaPanel;

    // Gosti section
    private final ListaGostijuPanel listaGostijuPanel;

    // Data exchange
    private final ListaEmisijaDataChangeListener listaEmisijaDataChangeListener;

    public EditEmisijaPanel(EmisijaModel emisija, ListaEmisijaDataChangeListener listaEmisijaDataChangeListener) throws IOException {
        super(emisija, DimensionsEnum.twoTimesTwo.getDimensions());

        this.emisija = emisija;
        this.listaEmisijaDataChangeListener = listaEmisijaDataChangeListener;
        this.selectedUrednik = emisija.getUrednik();
        this.selectedVoditelj = emisija.getVoditelj();
        this.selectedVrstaEmisije = emisija.getVrstaEmisije();


        listaGostijuPanel = new ListaGostijuPanel(emisija.getIdEmisije(), this, (JFrame) SwingUtilities.getWindowAncestor(this));
        listaGostijuPanel.setBorder(BorderFactory.createTitledBorder("Gosti"));

        listaEpizodaPanel = new ListaEpizodaPanel(emisija.getIdEmisije(), this, (JFrame) SwingUtilities.getWindowAncestor(this));
        listaEpizodaPanel.setBorder(BorderFactory.createTitledBorder("Epizode"));

        add(listaEpizodaPanel);
        add(listaGostijuPanel);

        addDeleteButton();

        addButtonsPanel();
    }

    private void addDeleteButton() {
        Runnable deleteAction = () -> {
            // Add logic for deleting the Emisija
            try {
                emisijaApiResources.deleteEmisija(emisija.getIdEmisije());

                // Emit delete event
                listaEmisijaDataChangeListener.onEmisijaDeleted();

                informationMessageDialog.showMessage(String.format("Emisija: %s uspjesno obrisana!", emisija.getNazivEmisije()));

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                parentFrame.dispose();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };

        JButton deleteButton = new DeleteButton(deleteAction);

        buttonsPanel.add(deleteButton);
    }

    @Override
    protected void onSaveButtonClicked() throws IOException {
        emisijaApiResources.updateEmisija(emisija, emisija.getIdEmisije());
    }

    @Override
    protected void setNazivEmisijeInfo() {
        nazivEmisije.setText(emisija.getNazivEmisije());
    }

    @Override
    protected void setOpisEmisijeInfo() {
        opisEmisije.setText(emisija.getOpisEmisije());
    }

    @Override
    protected void setVoditeljInfo() {
        voditeljLabelsData.getIme().setText(emisija.getVoditelj().getImeVoditelja());
        voditeljLabelsData.getPrezime().setText(emisija.getVoditelj().getPrezimeVoditelja());
        voditeljLabelsData.getKontaktTelefon().setText(emisija.getVoditelj().getKontaktTelefonVoditelja());
    }

    @Override
    protected void setUrednikInfo() {
        urednikLabelsData.getIme().setText(emisija.getUrednik().getImeUrednika());
        urednikLabelsData.getPrezime().setText(emisija.getUrednik().getPrezimeUrednika());
        urednikLabelsData.getKontaktTelefon().setText(emisija.getUrednik().getKontaktTelefonUrednika());
    }

    @Override
    protected void setVrstaEmisijeInfo() {
        vrstaEmisijeLabel.setText(emisija.getVrstaEmisije().getNazivVrsteEmisije());
    }

    @Override
    protected void setTrajanjeEmisijeInfo() {
        trajanjeEmisije.setText(String.valueOf(emisija.getTrajanjeEmisije()));
    }

    @Override
    protected void setOcjenaEmisijeInfo() {
        ocjenaEmisije.setText(String.valueOf(emisija.getOcjenaEmisije()));
    }

    @Override
    public void onGostSelected(GostModel data) {
        try {
            List<GostModel> gosti = emisija.getGosti();
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

    @Override
    public void onEpizodaDeleted() throws IOException {
        listaEpizodaPanel.updateList();
    }

    @Override
    public void onGostDeleted() throws IOException {
        listaGostijuPanel.updateList();
        emisija.setGosti(listaGostijuPanel.getGosti());
    }
}

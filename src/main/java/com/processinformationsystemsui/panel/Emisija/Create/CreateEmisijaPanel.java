package com.processinformationsystemsui.panel.Emisija.Create;

import com.processinformationsystemsui.common.DimensionsEnum;
import com.processinformationsystemsui.model.*;
import com.processinformationsystemsui.panel.Emisija.EmisijaPanel;
import com.processinformationsystemsui.panel.Emisija.ListaEmisija.Data.ListaEmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Epizoda.Create.CreateEpizodaModel;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;

public class CreateEmisijaPanel extends EmisijaPanel {
    private final ListaEmisijaDataChangeListener listaEmisijaDataChangeListener;
    public CreateEmisijaPanel(EmisijaModel emisija, ListaEmisijaDataChangeListener listaEmisijaDataChangeListener) throws IOException {
        super(emisija, DimensionsEnum.twoTimesOne.getDimensions());

        this.listaEmisijaDataChangeListener = listaEmisijaDataChangeListener;

        this.selectedVoditelj = null;
        this.selectedUrednik = null;
        this.selectedVrstaEmisije = null;

        addButtonsPanel();
    }

    @Override
    protected void onSaveButtonClicked() throws IOException {
        CreateEmisijaModel newEmisija = new CreateEmisijaModel(
                emisija.getNazivEmisije(),
                emisija.getOpisEmisije(),
                emisija.getTrajanjeEmisije(),
                emisija.getOcjenaEmisije(),
                emisija.getVrstaEmisije().getIdVrsteEmisije(),
                emisija.getVoditelj().getIdVoditelja(),
                emisija.getUrednik().getIdUrednika(),
                new HashSet<>()
        );

        emisijaApiResources.createEmisija(newEmisija);

        // Emit created event
        listaEmisijaDataChangeListener.onEmisijaCreated();

        SwingUtilities.getWindowAncestor(this).dispose();
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
}

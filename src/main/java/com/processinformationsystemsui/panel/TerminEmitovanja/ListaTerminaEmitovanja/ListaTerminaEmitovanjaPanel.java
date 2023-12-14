package com.processinformationsystemsui.panel.TerminEmitovanja.ListaTerminaEmitovanja;

import com.processinformationsystemsui.common.button.CreateButton;
import com.processinformationsystemsui.panel.Epizoda.Data.EpizodaDataChangeListener;
import com.processinformationsystemsui.panel.TerminEmitovanja.Data.TerminEmitovanjaDataChangeListener;
import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.EpizodaModel;
import com.processinformationsystemsui.model.TerminEmitovanjaModel;
import com.processinformationsystemsui.panel.TerminEmitovanja.Create.CreateTerminEmitovanjaDialog;
import com.processinformationsystemsui.panel.TerminEmitovanja.Delete.DeleteTerminEmitovanjaDialog;
import com.processinformatuionsystemsui.api.TerminEmitovanjaApiResources;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ListaTerminaEmitovanjaPanel extends BaseListPanel<TerminEmitovanjaModel> implements TerminEmitovanjaDataChangeListener {
    private final TerminEmitovanjaApiResources apiResources = new TerminEmitovanjaApiResources();
    private final EpizodaModel epizoda;
    private final JFrame parentFrame;
    private final EpizodaDataChangeListener epizodaDataChangeListener;

    public ListaTerminaEmitovanjaPanel(EpizodaModel epizoda,
                                       EpizodaDataChangeListener epizodaDataChangeListener,
                                       JFrame parentFrame) throws IOException {
        super();

        this.parentFrame = parentFrame;
        this.epizodaDataChangeListener = epizodaDataChangeListener;

        this.epizoda = epizoda;

        addCreateButton();

        updateList();
    }

    private void addCreateButton() {
        Runnable createAction = () -> {
           CreateTerminEmitovanjaDialog dialog = new CreateTerminEmitovanjaDialog(parentFrame, epizodaDataChangeListener, epizoda);
           dialog.setVisible(true);
        };

        JButton createNewTimeAndDateButton = new CreateButton(createAction);
        buttonPane.add(createNewTimeAndDateButton);
    }

    @Override
    protected ListaTerminaEmitovanjaCellRenderer setCellRenderer() {
        return new ListaTerminaEmitovanjaCellRenderer();
    }

    @Override
    protected void onSelected(TerminEmitovanjaModel terminEmitovanja) {
        if (terminEmitovanja != null) {
            String deletionQuery = String.format("?idEpizode=%s&vrijemePocetka=%s", terminEmitovanja.getEpizoda().getIdEpizode(), terminEmitovanja.getVrijemePocetka());
            DeleteTerminEmitovanjaDialog deleteTerminEmitovanjaDialog = new DeleteTerminEmitovanjaDialog(parentFrame,this, deletionQuery);
            deleteTerminEmitovanjaDialog.setVisible(true);
        }
    }

    @Override
    public void updateList() throws IOException {
        listModel.clear();

        // Get all termini emitovanja
        List<TerminEmitovanjaModel> terminiEmitovanja = apiResources.getAllTerminiByIdEpizode(epizoda.getIdEpizode());

        listModel.addAll(terminiEmitovanja);

        itemList.repaint();
    }

    @Override
    public void onDeleteTerminEmitovanja(String deletionQuery) throws IOException {
        apiResources.deleteTerminEmitovanja(deletionQuery);

        // Emit delete termin emitovanja from epizoda
        epizodaDataChangeListener.onTerminEmitovanjaDeleted();
    }
}


package com.processinformationsystemsui.panel.TerminEmitovanja.ListaTerminaEmitovanja;

import com.processinformationsystemsui.common.Common;
import com.processinformationsystemsui.panel.Epizoda.Data.EpizodaDataChangeListener;
import com.processinformationsystemsui.panel.TerminEmitovanja.Data.TerminEmitovanjaChangeListener;
import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.EpizodaModel;
import com.processinformationsystemsui.model.TerminEmitovanjaModel;
import com.processinformationsystemsui.panel.TerminEmitovanja.Create.CreateTerminEmitovanjaDialog;
import com.processinformationsystemsui.panel.TerminEmitovanja.Delete.DeleteTerminEmitovanjaDialog;
import com.processinformatuionsystemsui.api.TerminEmitovanjaApiResources;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ListaTerminaEmitovanjaPanel extends BaseListPanel<TerminEmitovanjaModel> implements TerminEmitovanjaChangeListener {
    private final TerminEmitovanjaApiResources apiResources = new TerminEmitovanjaApiResources();
    private final EpizodaModel epizoda;
    private final Boolean isFromMain = false;
    private final JFrame parentFrame;
    private final EpizodaDataChangeListener listener;

    public ListaTerminaEmitovanjaPanel(EpizodaModel epizoda,
                                       EpizodaDataChangeListener listener,
                                       JFrame parentFrame) throws IOException {
        super();

        this.parentFrame = parentFrame;
        this.listener = listener;

        this.epizoda = epizoda;

        addCreateButton();

        addRefreshButton();

        updateList();
    }

    private void addCreateButton() {
        JButton createNewTimeAndDateButton = new JButton("CREATE");
        buttonPane.add(createNewTimeAndDateButton);
        Common.addMouseListener(createNewTimeAndDateButton);

        createNewTimeAndDateButton.addActionListener(e -> {
            CreateTerminEmitovanjaDialog dialog = new CreateTerminEmitovanjaDialog(parentFrame, listener, epizoda);
            dialog.setVisible(true);
        });
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
    }
}


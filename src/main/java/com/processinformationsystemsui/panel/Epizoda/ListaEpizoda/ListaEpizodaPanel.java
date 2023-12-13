package com.processinformationsystemsui.panel.Epizoda.ListaEpizoda;

import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.EpizodaModel;
import com.processinformationsystemsui.panel.Epizoda.Create.CreateEpizodaDialog;
import com.processinformationsystemsui.panel.Epizoda.Epizoda;
import com.processinformationsystemsui.common.Common;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Epizoda.EpizodaPanel;
import com.processinformatuionsystemsui.api.EpizodaApiResources;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ListaEpizodaPanel extends BaseListPanel<EpizodaModel> {
    private final EpizodaApiResources apiResources = new EpizodaApiResources();
    private final String idEmisije;
    private final JFrame parentFrame;
    private final EmisijaDataChangeListener listener;

    public ListaEpizodaPanel(String idEmisije,
                             EmisijaDataChangeListener listener,
                             JFrame parentFrame) throws IOException {
        super();

        this.parentFrame = parentFrame;
        this.listener = listener;

        this.idEmisije = idEmisije;

        addCreateButton();

        addRefreshButton();

        updateList();
    }

    private void addCreateButton() {
        JButton createNewEpisodeButton = new JButton("CREATE");
        buttonPane.add(createNewEpisodeButton);
        Common.addMouseListener(createNewEpisodeButton);

        createNewEpisodeButton.addActionListener(e -> {
            CreateEpizodaDialog dialog = new CreateEpizodaDialog(parentFrame, listener);
            dialog.setVisible(true);
        });
    }

    @Override
    protected ListaEpizodaCellRenderer setCellRenderer() {
        return new ListaEpizodaCellRenderer();
    }

    @Override
    protected void onSelected(EpizodaModel epizoda) throws IOException {
        if (epizoda != null) {
            String title = String.format("%s: %s", epizoda.getEmisija().getNazivEmisije(), epizoda.getNazivEpizode());
            new Epizoda(title, new EpizodaPanel(epizoda));
        }
    }

    @Override
    public void updateList() throws IOException {
        listModel.clear();

        // Get all epizoda
        List<EpizodaModel> epizode = apiResources.getAllEpizode(idEmisije);

        listModel.addAll(epizode);

        itemList.repaint();
    }
}


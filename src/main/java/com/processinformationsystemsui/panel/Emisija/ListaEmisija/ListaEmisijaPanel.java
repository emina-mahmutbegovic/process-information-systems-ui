package com.processinformationsystemsui.panel.Emisija.ListaEmisija;

import com.processinformationsystemsui.common.Common;
import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.EmisijaModel;
import com.processinformationsystemsui.panel.Emisija.Create.CreateEmisijaModel;
import com.processinformationsystemsui.panel.Emisija.Create.CreateEmisijaPanel;
import com.processinformationsystemsui.panel.Emisija.Emisija;
import com.processinformationsystemsui.panel.Emisija.EmisijaPanel;
import com.processinformationsystemsui.panel.Epizoda.Create.CreateEpizodaDialog;
import com.processinformatuionsystemsui.api.EmisijaApiResources;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ListaEmisijaPanel extends BaseListPanel<EmisijaModel> {
    private final EmisijaApiResources apiResources = new EmisijaApiResources();

    public ListaEmisijaPanel() throws IOException {
        super();

        addCreateButton();
        addRefreshButton();

        updateList();
    }

    private void addCreateButton() {
        JButton createNewShowButton = new JButton("CREATE");
        buttonPane.add(createNewShowButton);
        Common.addMouseListener(createNewShowButton);

        createNewShowButton.addActionListener(e -> {
            EmisijaModel blankEmisija = new EmisijaModel("", "", "", 0, 0, null, null, null, null);
            try {
                new Emisija("Kreiranje nove emisije", new CreateEmisijaPanel(blankEmisija));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @Override
    protected ListaEmisijaCellRenderer setCellRenderer() {
        return new ListaEmisijaCellRenderer();
    }

    @Override
    protected void onSelected(EmisijaModel emisija) throws IOException {
        if(emisija != null) new Emisija(emisija.getNazivEmisije(), new EmisijaPanel(emisija));
    }

    @Override
    public void updateList() throws IOException {
        listModel.clear();

        // Get all emisije
        List<EmisijaModel> emisije = apiResources.getAllEmisija();

        listModel.addAll(emisije);

        itemList.repaint();
    }
}


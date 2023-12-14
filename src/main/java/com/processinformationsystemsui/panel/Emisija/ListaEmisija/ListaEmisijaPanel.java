package com.processinformationsystemsui.panel.Emisija.ListaEmisija;

import com.processinformationsystemsui.common.Common;
import com.processinformationsystemsui.common.button.CreateButton;
import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.EmisijaModel;
import com.processinformationsystemsui.panel.Emisija.Create.CreateEmisijaPanel;
import com.processinformationsystemsui.panel.Emisija.Emisija;
import com.processinformationsystemsui.panel.Emisija.Edit.EditEmisijaPanel;
import com.processinformationsystemsui.panel.Emisija.ListaEmisija.Data.ListaEmisijaDataChangeListener;
import com.processinformatuionsystemsui.api.EmisijaApiResources;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaEmisijaPanel extends BaseListPanel<EmisijaModel> implements ListaEmisijaDataChangeListener {
    private final EmisijaApiResources apiResources = new EmisijaApiResources();

    public ListaEmisijaPanel() throws IOException {
        super();

        addCreateButton();

        updateList();
    }

    private void addCreateButton() {
        Runnable createAction = () -> {
            EmisijaModel blankEmisija = new EmisijaModel(null, "", "", 0, 0, null, null, null, new ArrayList<>());
            try {
                new Emisija("Kreiranje nove emisije", new CreateEmisijaPanel(blankEmisija, this));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };

        JButton createNewShowButton = new CreateButton(createAction);
        buttonPane.add(createNewShowButton);
    }

    @Override
    protected ListaEmisijaCellRenderer setCellRenderer() {
        return new ListaEmisijaCellRenderer();
    }

    @Override
    protected void onSelected(EmisijaModel emisija) throws IOException {
        if(emisija != null) new Emisija(emisija.getNazivEmisije(), new EditEmisijaPanel(emisija, this));
    }

    @Override
    public void updateList() throws IOException {
        listModel.clear();

        // Get all emisije
        List<EmisijaModel> emisije = apiResources.getAllEmisija();

        listModel.addAll(emisije);

        itemList.repaint();
    }

    @Override
    public void onEmisijaCreated() throws IOException {
        updateList();
    }

    @Override
    public void onEmisijaDeleted() throws IOException {
        updateList();
    }
}


package com.processinformationsystemsui.panel.Emisija.ListaEmisija;

import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.EmisijaModel;
import com.processinformationsystemsui.panel.Emisija.Emisija;
import com.processinformatuionsystemsui.api.EmisijaApiResources;

import java.io.IOException;
import java.util.List;

public class ListaEmisijaPanel extends BaseListPanel<EmisijaModel> {
    private final EmisijaApiResources apiResources = new EmisijaApiResources();

    public ListaEmisijaPanel() throws IOException {
        super();

        addRefreshButton();

        updateList();
    }

    @Override
    protected ListaEmisijaCellRenderer setCellRenderer() {
        return new ListaEmisijaCellRenderer();
    }

    @Override
    protected void onSelected(EmisijaModel emisija) throws IOException {
        if(emisija != null) new Emisija(emisija);
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


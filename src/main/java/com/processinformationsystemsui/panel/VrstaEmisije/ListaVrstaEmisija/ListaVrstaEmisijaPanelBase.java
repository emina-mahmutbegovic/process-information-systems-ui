package com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija;

import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.VrstaEmisijeModel;
import com.processinformationsystemsui.panel.VrstaEmisije.Data.VrstaEmisijeDataChangeListener;
import com.processinformationsystemsui.panel.VrstaEmisije.create.CreateVrstaEmisijeModel;
import com.processinformatuionsystemsui.api.VrstaEmisijeApiResources;

import java.io.IOException;
import java.util.List;

public class ListaVrstaEmisijaPanelBase extends BaseListPanel<VrstaEmisijeModel> implements VrstaEmisijeDataChangeListener {
    protected final VrstaEmisijeApiResources apiResources = new VrstaEmisijeApiResources();

    public ListaVrstaEmisijaPanelBase() throws IOException {
        super();

        updateList();
    }

    @Override
    protected ListaVrstaEmisijaCellRenderer setCellRenderer() {
        return new ListaVrstaEmisijaCellRenderer();
    }

    @Override
    protected void onSelected(VrstaEmisijeModel vrstaEmisije) throws IOException {
        // Implement in child classes
    }

    @Override
    protected void updateList() throws IOException {
        listModel.clear();

        // Get all vrste emisija
        List<VrstaEmisijeModel> vrsteEmisija = apiResources.getAllVrstaEmisije();

        listModel.addAll(vrsteEmisija);

        itemList.repaint();
    }

    @Override
    public void onVrstaEmisijeCreated(CreateVrstaEmisijeModel data) throws IOException {}
}

package com.processinformationsystemsui.panel.Gost.ListaGostiju;

import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.panel.Gost.Create.CreateGostModel;
import com.processinformationsystemsui.panel.Gost.Data.GostDataChangeListener;
import com.processinformatuionsystemsui.api.GostApiResources;
import com.processinformatuionsystemsui.api.GostujeApiResources;

import java.io.IOException;
import java.util.List;

public class ListaGostijuPanelBase extends BaseListPanel<GostModel> implements GostDataChangeListener {
    protected final GostApiResources gostAPIResources = new GostApiResources();
    protected final GostujeApiResources gostujeAPIResources = new GostujeApiResources();
    protected List<GostModel> gosti;


    public ListaGostijuPanelBase() throws IOException {
        super();

        updateList();
    }

    @Override
    public void updateList() throws IOException {}

    @Override
    protected ListaGostijuCellRenderer setCellRenderer() {
        return new ListaGostijuCellRenderer();
    }

    @Override
    protected void onSelected(GostModel gost) {}

    @Override
    public void onGostCreated(CreateGostModel data) throws IOException {

    }

    @Override
    public void onGostEdited() throws IOException {

    }

    @Override
    public void onGostDeleted() throws IOException {

    }
}

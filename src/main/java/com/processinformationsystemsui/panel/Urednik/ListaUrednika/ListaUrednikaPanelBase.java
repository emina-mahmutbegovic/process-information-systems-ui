package com.processinformationsystemsui.panel.Urednik.ListaUrednika;

import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.panel.Urednik.Data.UrednikDataChangeListener;
import com.processinformationsystemsui.panel.Urednik.ListaUrednika.Create.CreateUrednikModel;
import com.processinformatuionsystemsui.api.UrednikApiResources;

import java.io.IOException;
import java.util.List;

public class ListaUrednikaPanelBase extends BaseListPanel<UrednikModel> implements UrednikDataChangeListener {
    private final UrednikApiResources apiResources = new UrednikApiResources();
    public ListaUrednikaPanelBase() throws IOException {
        super();

        updateList();
    }

    @Override
    protected ListaUrednikaCellRenderer setCellRenderer() {
        return new ListaUrednikaCellRenderer();
    }

    @Override
    protected void onSelected(UrednikModel urednik) {}

    @Override
    protected void updateList() throws IOException {
        listModel.clear();

        // Get all urednici
        List<UrednikModel> urednici = apiResources.getAllUrednici();

        listModel.addAll(urednici);

        itemList.repaint();
    }

    @Override
    public void onUrednikCreated(CreateUrednikModel data) throws IOException {
        apiResources.createUrednik(data);

        updateList();
    }

    @Override
    public void onUrednikEdited() throws IOException {
        updateList();
    }

    @Override
    public void onUrednikDeleted() throws IOException {
        updateList();
    }
}

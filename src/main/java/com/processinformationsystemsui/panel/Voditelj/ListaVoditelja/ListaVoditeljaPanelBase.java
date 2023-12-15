package com.processinformationsystemsui.panel.Voditelj.ListaVoditelja;

import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.panel.Voditelj.Create.CreateVoditeljModel;
import com.processinformationsystemsui.panel.Voditelj.Data.VoditeljiDataChangeListener;
import com.processinformatuionsystemsui.api.VoditeljApiResources;

import java.io.IOException;
import java.util.List;

public class ListaVoditeljaPanelBase extends BaseListPanel<VoditeljModel> implements VoditeljiDataChangeListener {
    private final VoditeljApiResources apiResources = new VoditeljApiResources();

    public ListaVoditeljaPanelBase() throws IOException {
        super();

        updateList();
    }

    @Override
    protected ListaVoditeljaCellRenderer setCellRenderer() {
        return new ListaVoditeljaCellRenderer();
    }

    @Override
    protected void onSelected(VoditeljModel voditelj) { }

    @Override
    protected void updateList() throws IOException {
        listModel.clear();

        // Get all voditelji
        List<VoditeljModel> voditelji = apiResources.getAllVoditelji();

        listModel.addAll(voditelji);

        itemList.repaint();
    }

    @Override
    public void onVoditeljCreated(CreateVoditeljModel createVoditeljModel) throws IOException {
        apiResources.createVoditelj(createVoditeljModel);

        updateList();
    }

    @Override
    public void onVoditeljEdited() throws IOException {
        updateList();
    }

    @Override
    public void onVoditeljDeleted() throws IOException {
        updateList();
    }


}

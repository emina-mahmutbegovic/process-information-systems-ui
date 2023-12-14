package com.processinformationsystemsui.panel.Urednik.ListaUrednika;

import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformatuionsystemsui.api.UrednikApiResources;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ListaUrednikaPanel extends BaseListPanel<UrednikModel> {
    private final UrednikApiResources apiResources = new UrednikApiResources();
    private final EmisijaDataChangeListener listener;
    private final Boolean isFromEmisijaPanel;

    public ListaUrednikaPanel(EmisijaDataChangeListener listener, Boolean isFromEmisijaPanel) throws IOException {
        super();

        this.listener = listener;
        this.isFromEmisijaPanel = isFromEmisijaPanel;

        updateList();
    }

    @Override
    protected ListaUrednikaCellRenderer setCellRenderer() {
        return new ListaUrednikaCellRenderer();
    }

    @Override
    protected void onSelected(UrednikModel urednik) {
        if(Boolean.TRUE.equals(isFromEmisijaPanel) && urednik != null) {
            sendDataToParent(urednik.getIdUrednika());

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            parentFrame.dispose();
        }
    }

    @Override
    protected void updateList() throws IOException {
        listModel.clear();

        // Get all urednici
        List<UrednikModel> urednici = apiResources.getAllUrednici();

        listModel.addAll(urednici);

        itemList.repaint();
    }

    // Method to send data to the parent panel
    private void sendDataToParent(Object data) {
        if (listener != null) {
            listener.onUrednikSelected(data);
        }
    }
}

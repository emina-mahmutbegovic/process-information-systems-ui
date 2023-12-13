package com.processinformationsystemsui.panel.Voditelj.ListaVoditelja;

import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.common.EmisijaDataChangeListener;
import com.processinformatuionsystemsui.api.VoditeljApiResources;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ListaVoditeljaPanel extends BaseListPanel<VoditeljModel> {
    private final VoditeljApiResources apiResources = new VoditeljApiResources();

    private final EmisijaDataChangeListener listener;
    private final Boolean isFromEmisijaPanel;

    public ListaVoditeljaPanel(EmisijaDataChangeListener listener, Boolean isFromEmisijaPanel) throws IOException {
        super();

        this.listener = listener;
        this.isFromEmisijaPanel = isFromEmisijaPanel;

        if(Boolean.FALSE.equals(isFromEmisijaPanel)) {
            addRefreshButton();
        }

        updateList();
    }

    @Override
    protected ListaVoditeljaCellRenderer setCellRenderer() {
        return new ListaVoditeljaCellRenderer();
    }

    @Override
    protected void onSelected(VoditeljModel voditelj) {
        if(Boolean.TRUE.equals(isFromEmisijaPanel) && voditelj != null) {
            sendDataToParent(voditelj.getIdVoditelja());

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            parentFrame.dispose();
        }
    }

    @Override
    protected void updateList() throws IOException {
        listModel.clear();

        // Get all voditelji
        List<VoditeljModel> voditelji = apiResources.getAllVoditelji();

        listModel.addAll(voditelji);

        itemList.repaint();
    }

    // Method to send data to the parent panel
    private void sendDataToParent(Object data) {
        if (listener != null) {
            listener.onVoditeljSelected(data);
        }
    }
}

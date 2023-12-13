package com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija;

import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.VrstaEmisijeModel;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformatuionsystemsui.api.VrstaEmisijeApiResources;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ListaVrstaEmisijaPanel extends BaseListPanel<VrstaEmisijeModel> {
    private final VrstaEmisijeApiResources apiResources = new VrstaEmisijeApiResources();

    private final EmisijaDataChangeListener listener;
    private final Boolean isFromEmisijaPanel;

    public ListaVrstaEmisijaPanel(EmisijaDataChangeListener listener, Boolean isFromEmisijaPanel) throws IOException {
        super();

        this.listener = listener;
        this.isFromEmisijaPanel = isFromEmisijaPanel;

        if(Boolean.FALSE.equals(isFromEmisijaPanel)) {
            addRefreshButton();
        }

        updateList();
    }

    @Override
    protected ListaVrstaEmisijaCellRenderer setCellRenderer() {
        return new ListaVrstaEmisijaCellRenderer();
    }

    @Override
    protected void onSelected(VrstaEmisijeModel vrstaEmisije) throws IOException {
        if(Boolean.TRUE.equals(isFromEmisijaPanel) && vrstaEmisije != null) {
            sendDataToParent(vrstaEmisije.getIdVrsteEmisije());

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            parentFrame.dispose();
        }
    }

    @Override
    protected void updateList() throws IOException {
        listModel.clear();

        // Get all vrste emisija
        List<VrstaEmisijeModel> vrsteEmisija = apiResources.getAllVrstaEmisije();

        listModel.addAll(vrsteEmisija);

        itemList.repaint();
    }

    // Method to send data to the parent panel
    private void sendDataToParent(Object data) {
        if (listener != null) {
            listener.onVrstaEmisijeSelected(data);
        }
    }
}

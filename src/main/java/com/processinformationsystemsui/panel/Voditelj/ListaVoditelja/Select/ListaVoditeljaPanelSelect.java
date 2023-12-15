package com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.Select;

import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.ListaVoditeljaPanelBase;

import javax.swing.*;
import java.io.IOException;

public class ListaVoditeljaPanelSelect extends ListaVoditeljaPanelBase {
    private final EmisijaDataChangeListener listener;

    public ListaVoditeljaPanelSelect(EmisijaDataChangeListener listener) throws IOException {
        super();

        this.listener = listener;
    }

    @Override
    protected void onSelected(VoditeljModel voditelj) {
        if(voditelj != null) {
            sendDataToParent(voditelj.getIdVoditelja());

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            parentFrame.dispose();
        }
    }

    // Method to send data to the parent panel
    private void sendDataToParent(Object data) {
        if (listener != null) {
            listener.onVoditeljSelected(data);
        }
    }
}

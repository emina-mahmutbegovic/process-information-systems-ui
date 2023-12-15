package com.processinformationsystemsui.panel.Urednik.ListaUrednika.Select;

import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Urednik.ListaUrednika.ListaUrednikaPanelBase;

import javax.swing.*;
import java.io.IOException;

public class ListaUrednikaPanelSelect extends ListaUrednikaPanelBase {
    private final EmisijaDataChangeListener listener;

    public ListaUrednikaPanelSelect(EmisijaDataChangeListener listener) throws IOException {
        super();

        this.listener = listener;
    }

    @Override
    protected void onSelected(UrednikModel urednik) {
        if(urednik != null) {
            sendDataToParent(urednik.getIdUrednika());

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            parentFrame.dispose();
        }
    }

    // Method to send data to the parent panel
    private void sendDataToParent(Object data) {
        if (listener != null) {
            listener.onUrednikSelected(data);
        }
    }
}

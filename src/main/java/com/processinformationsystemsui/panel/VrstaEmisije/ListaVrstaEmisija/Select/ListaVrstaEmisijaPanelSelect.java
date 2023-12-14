package com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.Select;

import com.processinformationsystemsui.model.VrstaEmisijeModel;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.ListaVrstaEmisijaPanelBase;

import javax.swing.*;
import java.io.IOException;

public class ListaVrstaEmisijaPanelSelect extends ListaVrstaEmisijaPanelBase {
    private final EmisijaDataChangeListener listener;

    public ListaVrstaEmisijaPanelSelect(EmisijaDataChangeListener listener) throws IOException {
        super();

        this.listener = listener;
    }

    @Override
    protected void onSelected(VrstaEmisijeModel vrstaEmisije) {
        if(vrstaEmisije != null) {
            sendDataToParent(vrstaEmisije.getIdVrsteEmisije());

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            parentFrame.dispose();
        }
    }

    // Method to send data to the parent panel
    private void sendDataToParent(Object data) {
        if (listener != null) {
            listener.onVrstaEmisijeSelected(data);
        }
    }
}

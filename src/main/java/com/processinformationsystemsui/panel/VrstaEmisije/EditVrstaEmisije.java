package com.processinformationsystemsui.panel.VrstaEmisije;

import com.processinformationsystemsui.common.frame.BaseFrame;
import com.processinformationsystemsui.model.VrstaEmisijeModel;
import com.processinformationsystemsui.panel.VrstaEmisije.Data.VrstaEmisijeDataChangeListener;

public class EditVrstaEmisije extends BaseFrame {
    public EditVrstaEmisije(VrstaEmisijeModel vrstaEmisije, VrstaEmisijeDataChangeListener listener) {
        super(vrstaEmisije.getNazivVrsteEmisije());

        // Create an instance of EditVrstaEmisije panel
        EditVrstaEmisijePanel editVrstaEmisijePanel = new EditVrstaEmisijePanel(vrstaEmisije, listener);

        // Set the EditVrstaEmisije panel as the content pane
        setContentPane(editVrstaEmisijePanel);
    }
}

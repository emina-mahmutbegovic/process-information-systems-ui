package com.processinformationsystemsui.panel.VrstaEmisije;

import com.processinformationsystemsui.common.frame.BaseFrame;
import com.processinformationsystemsui.model.VrstaEmisijeModel;

public class EditVrstaEmisije extends BaseFrame {
    public EditVrstaEmisije(VrstaEmisijeModel vrstaEmisije) {
        super(vrstaEmisije.getNazivVrsteEmisije());

        // Create an instance of EditVrstaEmisije panel
        EditVrstaEmisijePanel editVrstaEmisijePanel = new EditVrstaEmisijePanel(vrstaEmisije, this);

        // Set the EditVrstaEmisije panel as the content pane
        setContentPane(editVrstaEmisijePanel);
    }
}

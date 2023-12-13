package com.processinformationsystemsui.panel.Gost.Edit;

import com.processinformationsystemsui.common.frame.BaseFrame;
import com.processinformationsystemsui.model.GostModel;

public class EditGost extends BaseFrame {
    public EditGost(GostModel gost) {
        super(String.format("%s %s", gost.getImeGosta(), gost.getPrezimeGosta()));

        // Create an instance of EditGost panel
        EditGostPanel editGostPanel = new EditGostPanel(gost, this);

        // Set the EditGost panel as the content pane
        setContentPane(editGostPanel);
    }
}

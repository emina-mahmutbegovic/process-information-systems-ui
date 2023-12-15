package com.processinformationsystemsui.panel.Gost.Edit;

import com.processinformationsystemsui.common.frame.BaseFrame;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.panel.Gost.Data.GostDataChangeListener;

public class EditGost extends BaseFrame {
    public EditGost(GostModel gost, GostDataChangeListener listener) {
        super(String.format("%s %s", gost.getImeGosta(), gost.getPrezimeGosta()));

        // Create an instance of EditGost panel
        EditGostPanel editGostPanel = new EditGostPanel(gost, listener);

        // Set the EditGost panel as the content pane
        setContentPane(editGostPanel);
    }
}

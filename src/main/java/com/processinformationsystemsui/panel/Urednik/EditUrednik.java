package com.processinformationsystemsui.panel.Urednik;

import com.processinformationsystemsui.common.frame.BaseFrame;
import com.processinformationsystemsui.model.UrednikModel;

public class EditUrednik extends BaseFrame {
    public EditUrednik(UrednikModel urednik) {
        super(String.format("%s %s", urednik.getImeUrednika(), urednik.getPrezimeUrednika()));

        // Create an instance of EditUrednik panel
        EditUrednikPanel editUrednikPanel = new EditUrednikPanel(urednik, this);

        // Set the EditUrednik panel as the content pane
        setContentPane(editUrednikPanel);
    }
}

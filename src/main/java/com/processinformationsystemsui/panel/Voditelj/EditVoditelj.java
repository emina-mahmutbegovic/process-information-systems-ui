package com.processinformationsystemsui.panel.Voditelj;

import com.processinformationsystemsui.common.frame.BaseFrame;
import com.processinformationsystemsui.model.VoditeljModel;

public class EditVoditelj extends BaseFrame {
    public EditVoditelj (VoditeljModel voditelj) {
        super(String.format("%s %s", voditelj.getImeVoditelja(), voditelj.getPrezimeVoditelja()));

        // Create an instance of EditVoditelj panel
        EditVoditeljPanel editVoditeljPanel = new EditVoditeljPanel(voditelj, this);

        // Set the EditVoditelj panel as the content pane
        setContentPane(editVoditeljPanel);
    }
}

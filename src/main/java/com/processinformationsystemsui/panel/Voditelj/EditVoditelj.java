package com.processinformationsystemsui.panel.Voditelj;

import com.processinformationsystemsui.common.frame.BaseFrame;
import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.panel.Voditelj.Data.VoditeljiDataChangeListener;

public class EditVoditelj extends BaseFrame {
    public EditVoditelj (VoditeljModel voditelj, VoditeljiDataChangeListener listener) {
        super(String.format("%s %s", voditelj.getImeVoditelja(), voditelj.getPrezimeVoditelja()));

        // Create an instance of EditVoditelj panel
        EditVoditeljPanel editVoditeljPanel = new EditVoditeljPanel(voditelj, listener);

        // Set the EditVoditelj panel as the content pane
        setContentPane(editVoditeljPanel);
    }
}

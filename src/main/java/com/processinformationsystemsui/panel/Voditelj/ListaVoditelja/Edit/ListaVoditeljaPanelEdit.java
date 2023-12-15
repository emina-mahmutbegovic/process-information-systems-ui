package com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.Edit;

import com.processinformationsystemsui.common.button.CreateButton;
import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.panel.Voditelj.Create.CreateVoditeljDialog;
import com.processinformationsystemsui.panel.Voditelj.EditVoditelj;
import com.processinformationsystemsui.panel.Voditelj.ListaVoditelja.ListaVoditeljaPanelBase;

import javax.swing.*;
import java.io.IOException;

public class ListaVoditeljaPanelEdit extends ListaVoditeljaPanelBase {

    public ListaVoditeljaPanelEdit() throws IOException {
        super();

        addCreateButton();

        updateList();
    }

    @Override
    protected void onSelected(VoditeljModel voditelj) {
        if(voditelj != null) {
            new EditVoditelj(voditelj, this);
        }
    }

    private void addCreateButton() {
        Runnable createAction = () -> {
            CreateVoditeljDialog dialog = new CreateVoditeljDialog((JFrame) SwingUtilities.getWindowAncestor(this), this);
            dialog.setVisible(true);
        };

        JButton createNewShowButton = new CreateButton(createAction);
        buttonPane.add(createNewShowButton);
    }
}

package com.processinformationsystemsui.panel.Urednik.ListaUrednika.Edit;

import com.processinformationsystemsui.common.button.CreateButton;
import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.panel.Urednik.EditUrednik;
import com.processinformationsystemsui.panel.Urednik.ListaUrednika.Create.CreateUrednikDialog;
import com.processinformationsystemsui.panel.Urednik.ListaUrednika.ListaUrednikaPanelBase;

import javax.swing.*;
import java.io.IOException;

public class ListaUrednikaPanelEdit extends ListaUrednikaPanelBase {

    public ListaUrednikaPanelEdit() throws IOException {
        super();

        addCreateButton();

        updateList();
    }

    @Override
    protected void onSelected(UrednikModel urednik) {
        if(urednik != null) {
            new EditUrednik(urednik, this);
        }
    }

    private void addCreateButton() {
        Runnable createAction = () -> {
            CreateUrednikDialog dialog = new CreateUrednikDialog((JFrame) SwingUtilities.getWindowAncestor(this), this);
            dialog.setVisible(true);
        };

        JButton createNewShowButton = new CreateButton(createAction);
        buttonPane.add(createNewShowButton);
    }
}

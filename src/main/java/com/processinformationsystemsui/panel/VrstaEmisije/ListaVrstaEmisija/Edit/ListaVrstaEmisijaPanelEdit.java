package com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.Edit;

import com.processinformationsystemsui.common.button.CreateButton;
import com.processinformationsystemsui.model.VrstaEmisijeModel;
import com.processinformationsystemsui.panel.VrstaEmisije.EditVrstaEmisije;
import com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija.ListaVrstaEmisijaPanelBase;
import com.processinformationsystemsui.panel.VrstaEmisije.Create.CreateVrstaEmisijeDialog;

import javax.swing.*;
import java.io.IOException;

public class ListaVrstaEmisijaPanelEdit extends ListaVrstaEmisijaPanelBase {

    public ListaVrstaEmisijaPanelEdit() throws IOException {
        super();

        addCreateButton();
    }

    private void addCreateButton() {
        Runnable createAction = () -> {
            CreateVrstaEmisijeDialog dialog = new CreateVrstaEmisijeDialog((JFrame) SwingUtilities.getWindowAncestor(this), this);
            dialog.setVisible(true);
        };

        JButton createNewShowButton = new CreateButton(createAction);
        buttonPane.add(createNewShowButton);
    }

    @Override
    protected void onSelected(VrstaEmisijeModel vrstaEmisije) {
        if(vrstaEmisije != null) {
            new EditVrstaEmisije(vrstaEmisije, this);
        }
    }
}

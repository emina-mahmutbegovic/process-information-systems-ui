package com.processinformationsystemsui.panel.Gost.ListaGostiju.Edit;

import com.processinformationsystemsui.common.button.CreateButton;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.panel.Gost.Create.CreateGostDialog;
import com.processinformationsystemsui.panel.Gost.Create.CreateGostModel;
import com.processinformationsystemsui.panel.Gost.Edit.EditGost;
import com.processinformationsystemsui.panel.Gost.ListaGostiju.ListaGostijuPanelBase;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;

public class ListaGostijuPanelEdit extends ListaGostijuPanelBase {

    public ListaGostijuPanelEdit() throws IOException {
        super();

        addCreateButton();

        updateList();
    }

    private void addCreateButton() {
        Runnable createAction = () -> {
            CreateGostDialog dialog = new CreateGostDialog((JFrame) SwingUtilities.getWindowAncestor(this), this);
            dialog.setVisible(true);
        };

        JButton createNewShowButton = new CreateButton(createAction);
        buttonPane.add(createNewShowButton);
    }

    @Override
    protected void onSelected(GostModel gost) {
        if(gost != null) {
            new EditGost(gost, this);
        }
    }

    @Override
    public void updateList() throws IOException {
        listModel.clear();

        // Find all gosti
        gosti = gostAPIResources.getAllGosti(new HashSet<>());

        listModel.addAll(gosti);

        itemList.repaint();
    }

    @Override
    public void onGostCreated(CreateGostModel data) throws IOException {
        gostAPIResources.createGost(data);

        updateList();
    }

    @Override
    public void onGostEdited() throws IOException {
        updateList();
    }

    @Override
    public void onGostDeleted() throws IOException {
        updateList();
    }
}

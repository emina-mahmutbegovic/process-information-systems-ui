package com.processinformationsystemsui.panel.Gost.ListaGostiju.Select;

import com.processinformationsystemsui.common.button.AddButton;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.model.GostujeModel;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformationsystemsui.panel.Gost.Add.ListaGostijuDialog;
import com.processinformationsystemsui.panel.Gost.EmisijaGostPanel;
import com.processinformationsystemsui.panel.Gost.Gost;
import com.processinformationsystemsui.panel.Gost.ListaGostiju.ListaGostijuPanelBase;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListaGostijuPanelSelect extends ListaGostijuPanelBase {
    private final JFrame parentFrame;
    private final EmisijaDataChangeListener listener;
    private final String idEmisije;

    public ListaGostijuPanelSelect(String idEmisije, EmisijaDataChangeListener listener, JFrame parentFrame) throws IOException {
        super();

        this.parentFrame = parentFrame;
        this.listener = listener;
        this.idEmisije = idEmisije;

        createAddNewGuestButton();

        updateList();
    }

    private void createAddNewGuestButton() {
        Runnable addAction = () -> {
            ListaGostijuDialog dialog;
            try {
                dialog = new ListaGostijuDialog(parentFrame, listener);
                dialog.setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };

        JButton addNewGuest = new AddButton(addAction);
        buttonPane.add(addNewGuest);
    }

    @Override
    public void updateList() throws IOException {
        listModel.clear();

        //Find all guests
        if(idEmisije == null) {
            gosti = new ArrayList<>();
        } else {
            // find guests by TV show id
            List<GostujeModel> gostuje = gostujeAPIResources.getAllGostujeByEmisijaId(idEmisije);
            Set<String> idGostiju = gostuje.stream()
                    .map(GostujeModel::getId_gosta) // Map each Gostuje object to its 'id' property
                    .collect(Collectors.toSet());

            if(idGostiju.isEmpty()) gosti = new ArrayList<>();
            else {
                // Find gosti for all ids
                gosti = gostAPIResources.getAllGosti(idGostiju);
            }
        }

        listModel.addAll(gosti);

        itemList.repaint();
    }

    @Override
    protected void onSelected(GostModel gost) {
        if(gost != null) {
            String title = String.format("%s %s", gost.getImeGosta(), gost.getPrezimeGosta());

            new Gost(title, new EmisijaGostPanel(gost, idEmisije, listener));
        }
    }

    public List<GostModel> getGosti() {
        return gosti;
    }
}

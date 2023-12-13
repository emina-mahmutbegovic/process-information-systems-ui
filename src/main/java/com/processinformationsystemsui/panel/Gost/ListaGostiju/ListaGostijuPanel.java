package com.processinformationsystemsui.panel.Gost.ListaGostiju;

import com.processinformationsystemsui.common.list.BaseListPanel;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.model.GostujeModel;
import com.processinformationsystemsui.panel.Gost.Add.ListaGostijuDialog;
import com.processinformationsystemsui.panel.Gost.EmisijaGostPanel;
import com.processinformationsystemsui.panel.Gost.Gost;
import com.processinformationsystemsui.common.Common;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformatuionsystemsui.api.GostApiResources;
import com.processinformatuionsystemsui.api.GostujeApiResources;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListaGostijuPanel extends BaseListPanel<GostModel> {
    private final GostApiResources gostAPIResources = new GostApiResources();
    private final GostujeApiResources gostujeAPIResources = new GostujeApiResources();

    private final String idEmisije;
    private final JFrame parentFrame;
    private final EmisijaDataChangeListener listener;

    public ListaGostijuPanel(String idEmisije, EmisijaDataChangeListener listener, JFrame parentFrame) throws IOException {
        super();

        this.parentFrame = parentFrame;
        this.listener = listener;
        this.idEmisije = idEmisije;

        createAddNewGuestButton();

        addRefreshButton();

        updateList();
    }

    private void createAddNewGuestButton() {
        JButton addNewGuest = new JButton("ADD");
        buttonPane.add(addNewGuest);
        Common.addMouseListener(addNewGuest);

        addNewGuest.addActionListener(e -> {
            ListaGostijuDialog dialog;
            try {
                dialog = new ListaGostijuDialog(parentFrame, listener);
                dialog.setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @Override
    public void updateList() throws IOException {
        listModel.clear();

        // Get all gosti
        List<GostModel> gosti;
        //Find all guests
        if(idEmisije.isEmpty()) {
            gosti = gostAPIResources.getAllGosti(new HashSet<>());
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
    protected ListaGostijuCellRenderer setCellRenderer() {
        return new ListaGostijuCellRenderer();
    }

    @Override
    protected void onSelected(GostModel gost) {
        if(gost != null) {
            String title = String.format("%s %s", gost.getImeGosta(), gost.getPrezimeGosta());

            new Gost(title, new EmisijaGostPanel(gost, idEmisije));
        }
    }
}

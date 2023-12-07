package com.processinformationsystemsui.panel.Epizoda.ListaEpizoda;

import com.processinformationsystemsui.model.EpizodaModel;
import com.processinformationsystemsui.panel.Epizoda.Epizoda;
import com.processinformationsystemsui.util.Common;
import com.processinformatuionsystemsui.api.ApiResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class ListaEpizodaPanel extends JPanel {
    private static final String URL_EXTENSION = "epizode";
    private final  DefaultListModel<EpizodaModel> listModel;
    private final JList<EpizodaModel> itemList;

    private final ApiResources<EpizodaModel> apiResources;
    public ListaEpizodaPanel(String idEmisije) throws IOException {
        apiResources = new ApiResources<>(EpizodaModel.class, URL_EXTENSION+"?idEmisije="+idEmisije);

        setLayout(new BorderLayout());

        // Create a DefaultListModel to manage the data in the list
        listModel = new DefaultListModel<>();

        // Create a JList with the DefaultListModel
        itemList = new JList<>(listModel);

        // Add a MouseListener to handle cursor changes
        itemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                itemList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                itemList.setCursor(Cursor.getDefaultCursor());
            }

        });

        // Set custom cell renderer to display TV show name only
        itemList.setCellRenderer(new ListaEpizodaCellRenderer());

        // Add a selection listener to the JList
        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Get the selected item and display a message
                EpizodaModel epizoda = itemList.getSelectedValue();
                new Epizoda(epizoda);
            }
        });

        // Create a JScrollPane to enable scrolling if there are many items
        JScrollPane scrollPane = new JScrollPane(itemList);

        // Add the JScrollPane to the panel
        add(scrollPane, BorderLayout.CENTER);

        // Add refresh button
        JButton refreshButton = new JButton("REFRESH LIST");
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(refreshButton);
        add(buttonPane, BorderLayout.EAST);

        Common.addMouseListener(refreshButton);

        refreshButton.addActionListener(e -> {
            try {
                updateList();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        updateList();
    }

    private void updateList() throws IOException {
        listModel.clear();

        // Get all epizoda
        List<EpizodaModel> epizode = apiResources.getAllElements();

        listModel.addAll(epizode);

        itemList.repaint();
    }
}


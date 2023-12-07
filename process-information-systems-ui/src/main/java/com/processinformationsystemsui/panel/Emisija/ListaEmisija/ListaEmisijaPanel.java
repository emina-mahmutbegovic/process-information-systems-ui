package com.processinformationsystemsui.panel.Emisija.ListaEmisija;

import com.processinformationsystemsui.model.EmisijaModel;
import com.processinformationsystemsui.panel.Emisija.Emisija;
import com.processinformationsystemsui.panel.Epizoda.ListaEpizoda.ListaEpizodaCellRenderer;
import com.processinformationsystemsui.util.Common;
import com.processinformatuionsystemsui.api.ApiResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class ListaEmisijaPanel extends JPanel {
    private static final String URL_EXTENSION = "emisije";
    private final  DefaultListModel<EmisijaModel> listModel;
    private final JList<EmisijaModel> itemList;

    private final ApiResources<EmisijaModel> apiResources = new ApiResources<>(EmisijaModel.class, URL_EXTENSION);

    public ListaEmisijaPanel() throws IOException {
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
        itemList.setCellRenderer(new ListaEmisijaCellRenderer());

        // Add a selection listener to the JList
        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Get the selected item and display a message
                EmisijaModel emisija = itemList.getSelectedValue();
                try {
                    new Emisija(emisija);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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

        // Create a button to add a new item to the list
//        JButton addButton = new JButton("Add Item");
//        addButton.addActionListener(e -> {
//            // Show a dialog to get the new item from the user
//            String newItem = JOptionPane.showInputDialog(ListaEmisijaPanel.this, "Enter new item:");
//            if (newItem != null && !newItem.isEmpty()) {
//                // Add the new item to the list
//                //listModel.addElement(newItem);
//            }
//        });

        // Add the button to the panel
        //add(addButton, BorderLayout.SOUTH);
    }

    private void updateList() throws IOException {
        listModel.clear();

        // Get all emisije
        List<EmisijaModel> emisije = apiResources.getAllElements();

        listModel.addAll(emisije);

        itemList.repaint();
    }

//    void deleteEmisija(String id) throws IOException {
//        URL url = new URL(BASE_URL+"/"+id);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//        try {
//            connection.setRequestMethod("DELETE");
//
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) {
//                JOptionPane.showMessageDialog(this, "Emisija uspjesno obrisana!");
//            } else {
//                throw new IOException("HTTP error code: " + connection.getResponseCode());
//            }
//        } catch (Exception ex) {
//            throw new IOException(String.format("Could not delete TV show with ID: %s", id), ex);
//        } finally {
//            connection.disconnect();
//        }
//    }
}


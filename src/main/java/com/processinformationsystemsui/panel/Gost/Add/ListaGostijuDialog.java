package com.processinformationsystemsui.panel.Gost.Add;

import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.panel.Gost.ListaGostiju.ListaGostijuCellRenderer;
import com.processinformationsystemsui.panel.Emisija.Data.EmisijaDataChangeListener;
import com.processinformatuionsystemsui.api.GostApiResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

// Custom JDialog class
public class ListaGostijuDialog extends JDialog {
    private final  DefaultListModel<GostModel> listModel;
    private final JList<GostModel> itemList;

    private final GostApiResources gostAPIResources = new GostApiResources();
    private final EmisijaDataChangeListener listener;

    public ListaGostijuDialog(JFrame parent, EmisijaDataChangeListener listener) throws IOException {
        super(parent, "Dodaj novog gosta", true);

        this.listener = listener;

        // Create a JPanel to hold the content within the JScrollPane
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Example layout

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

        // Set custom cell renderer to display TV guest name only
        itemList.setCellRenderer(new ListaGostijuCellRenderer());

        // Add a selection listener to the JList
        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Get the selected item and display a message
                GostModel gost = itemList.getSelectedValue();
                if(gost != null) sendDataToParent(gost);

                // Deselect the item programmatically
                itemList.clearSelection();

                // Close the dialog
                dispose();
            }
        });

        // Create a JScrollPane to enable scrolling if there are many items
        JScrollPane scrollPane = new JScrollPane(itemList);

        // Add the JScrollPane to the dialog's content pane
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Set dialog properties
        setSize(400, 300);
        setLocationRelativeTo(parent);

        updateList();
    }

    private void updateList() throws IOException {
        listModel.clear();

        // Get all gosti
        List<GostModel> gosti = gostAPIResources.getAllGosti(new HashSet<>());

        listModel.addAll(gosti);

        itemList.repaint();
    }

    // Method to send data to the parent panel
    private void sendDataToParent(GostModel gost) {
        if (listener != null) {
            listener.onGostSelected(gost);
        }
    }
}

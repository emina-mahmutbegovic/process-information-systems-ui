package com.processinformationsystemsui.common.list;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class BaseListPanel<T> extends JPanel {
    protected final  DefaultListModel<T> listModel;
    protected final JList<T> itemList;
    protected final JPanel buttonPane = new JPanel();

    public BaseListPanel() throws IOException {
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
        itemList.setCellRenderer(setCellRenderer());

        // Add a selection listener to the JList
        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Get the selected item and display a message
                T element = itemList.getSelectedValue();
                // Perform action upon selection
                try {
                    onSelected(element);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Deselect the item programmatically
                itemList.clearSelection();
            }
        });

        // Create a JScrollPane to enable scrolling if there are many items
        JScrollPane scrollPane = new JScrollPane(itemList);

        // Add the JScrollPane to the panel
        add(scrollPane, BorderLayout.CENTER);

        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        add(buttonPane, BorderLayout.EAST);
    }

    protected BaseListCellRenderer<T> setCellRenderer() {
        // Implement this in child classes
        return null;
    }

    protected void onSelected(T element) throws IOException {
        // Implement this in child classes
        System.out.println("List item selected");
    }

    protected void updateList() throws IOException {
        // Implement this in child classes
        System.out.println("Update list called");
    }
}

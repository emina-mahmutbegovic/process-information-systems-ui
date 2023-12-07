package com.processinformationsystemsui.panel.Emisija.ListaEmisija;

import com.processinformationsystemsui.model.EmisijaModel;

import javax.swing.*;
import java.awt.*;

public class ListaEmisijaCellRenderer extends JPanel implements ListCellRenderer<Object> {
    private final Font customFont = new Font("Monaco", Font.PLAIN, 14);
    private final JLabel label;

    public ListaEmisijaCellRenderer() {
        setLayout(new BorderLayout());

        label = new JLabel();
        add(label, BorderLayout.CENTER);
    }


    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        if (value instanceof EmisijaModel) {
            EmisijaModel emisija = (EmisijaModel) value;
            value = emisija.getNazivEmisije();
        }

        String numberedValue = (index + 1) + ". " + value;
        label.setText(numberedValue);

        // Set the custom font
        label.setFont(customFont);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setEnabled(list.isEnabled());
        setOpaque(true);

        return this;
    }
}


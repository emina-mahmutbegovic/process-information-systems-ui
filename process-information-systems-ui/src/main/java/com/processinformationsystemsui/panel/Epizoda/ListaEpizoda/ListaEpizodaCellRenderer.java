package com.processinformationsystemsui.panel.Epizoda.ListaEpizoda;

import com.processinformationsystemsui.model.EpizodaModel;

import javax.swing.*;
import java.awt.*;

public class ListaEpizodaCellRenderer extends JPanel implements ListCellRenderer<Object> {
    private final JLabel label;

    public ListaEpizodaCellRenderer() {
        setLayout(new BorderLayout());

        label = new JLabel();
        add(label, BorderLayout.CENTER);
    }


    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        if (value instanceof EpizodaModel) {
            EpizodaModel epizoda = (EpizodaModel) value;
            value = epizoda.getNazivEpizode();
        }

        String numberedValue = (index + 1) + ". " + value;
        label.setText(numberedValue);

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


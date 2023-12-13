package com.processinformationsystemsui.common.list;

import javax.swing.*;
import java.awt.*;

public class BaseListCellRenderer<T> extends JPanel implements ListCellRenderer<Object> {
    private final JLabel label;

    protected Object value;

    public BaseListCellRenderer() {
        setLayout(new BorderLayout());

        label = new JLabel();
        add(label, BorderLayout.CENTER);
    }


    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.value = value;

        value = setValue();

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

    protected Object setValue() {
        // Implement this in child classes
        return null;
    }
}

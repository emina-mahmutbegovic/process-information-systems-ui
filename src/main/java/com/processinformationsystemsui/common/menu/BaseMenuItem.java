package com.processinformationsystemsui.common.menu;

import javax.swing.*;
import java.awt.*;

public class BaseMenuItem extends JMenuItem {

    public BaseMenuItem(String title, Runnable action) {
        // Create menu items
        setText(title);
        Font menuItemFont = new Font("Monaco", Font.BOLD, 16);
        setFont(menuItemFont);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Set action
        addActionListener(e-> action.run());
    }
}

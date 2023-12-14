package com.processinformationsystemsui.common.button;

import java.awt.*;

public class AddButton extends BaseButton {
    public AddButton(Runnable action) {
        super(action);

        setText("ADD");
        setForeground(Color.BLUE);
    }
}

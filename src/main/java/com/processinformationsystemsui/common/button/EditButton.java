package com.processinformationsystemsui.common.button;

import java.awt.*;

public class EditButton extends BaseButton {
    public EditButton(Runnable action) {
        super(action);

        setText("EDIT");
        setForeground(Color.ORANGE);
    }
}

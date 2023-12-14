package com.processinformationsystemsui.common.button;

import java.awt.*;

public class CancelButton extends BaseButton {
    public CancelButton(Runnable action) {
        super(action);

        setText("CANCEL");
        setForeground(Color.red);
    }
}

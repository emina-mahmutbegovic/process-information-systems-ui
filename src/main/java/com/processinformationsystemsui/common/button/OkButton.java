package com.processinformationsystemsui.common.button;

import java.awt.*;

public class OkButton extends BaseButton {
    public OkButton(Runnable action) {
        super(action);

        setText("OK");
        setForeground(Color.BLUE);
    }
}

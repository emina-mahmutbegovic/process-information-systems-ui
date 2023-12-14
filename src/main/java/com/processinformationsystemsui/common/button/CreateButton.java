package com.processinformationsystemsui.common.button;

import java.awt.*;

public class CreateButton extends BaseButton {
    public CreateButton(Runnable action) {
        super(action);

        setText("CREATE");
        setForeground(Color.GREEN);
    }
}

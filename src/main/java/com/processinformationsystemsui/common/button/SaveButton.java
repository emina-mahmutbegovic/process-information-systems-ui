package com.processinformationsystemsui.common.button;
import java.awt.*;

public class SaveButton extends BaseButton {
    public SaveButton(Runnable action) {
        super(action);

        setText("SAVE");
        setForeground(Color.BLUE);
    }
}

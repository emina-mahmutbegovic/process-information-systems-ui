package com.processinformationsystemsui.common.button;
import java.awt.*;

public class DeleteButton extends BaseButton {
    public DeleteButton(Runnable action) {
        super(action);

        setText("DELETE");
        setForeground(Color.RED);
    }
}

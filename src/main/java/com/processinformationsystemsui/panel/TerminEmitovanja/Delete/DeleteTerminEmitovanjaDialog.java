package com.processinformationsystemsui.panel.TerminEmitovanja.Delete;

import com.processinformationsystemsui.panel.TerminEmitovanja.Data.TerminEmitovanjaDataChangeListener;
import com.processinformationsystemsui.common.dialog.delete.BaseDeleteElementDialog;

import javax.swing.*;
import java.io.IOException;

public class DeleteTerminEmitovanjaDialog extends BaseDeleteElementDialog {
    private final TerminEmitovanjaDataChangeListener listener;
    private final String deletionQuery;
    public DeleteTerminEmitovanjaDialog(JFrame parentFrame, TerminEmitovanjaDataChangeListener listener, String deletionQuery) {
        super(parentFrame, "Delete Termin Emitovanja");

        this.listener = listener;
        this.deletionQuery = deletionQuery;
    }

    @Override
    protected void emitItemDeletion() {
        try {
            listener.onDeleteTerminEmitovanja(deletionQuery);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

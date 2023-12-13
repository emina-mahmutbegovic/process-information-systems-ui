package com.processinformationsystemsui.panel.Urednik.ListaUrednika;

import com.processinformationsystemsui.common.list.BaseListCellRenderer;
import com.processinformationsystemsui.model.UrednikModel;

import javax.swing.*;
import java.awt.*;

public class ListaUrednikaCellRenderer extends BaseListCellRenderer<UrednikModel> {
    public ListaUrednikaCellRenderer() {
        super();
    }

    @Override
    protected Object setValue() {
        if (value instanceof UrednikModel) {
            UrednikModel urednik = (UrednikModel) value;
            return String.format("%s %s", urednik.getImeUrednika(), urednik.getPrezimeUrednika());
        }

        return null;
    }
}

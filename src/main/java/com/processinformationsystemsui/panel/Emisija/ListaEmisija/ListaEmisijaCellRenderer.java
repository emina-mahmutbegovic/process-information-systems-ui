package com.processinformationsystemsui.panel.Emisija.ListaEmisija;

import com.processinformationsystemsui.common.list.BaseListCellRenderer;
import com.processinformationsystemsui.model.EmisijaModel;
import com.processinformationsystemsui.panel.Emisija.Emisija;

import javax.swing.*;
import java.awt.*;

public class ListaEmisijaCellRenderer extends BaseListCellRenderer<EmisijaModel> {
    public ListaEmisijaCellRenderer() {
        super();
    }

    @Override
    protected Object setValue() {
        if (value instanceof EmisijaModel) {
            EmisijaModel emisija = (EmisijaModel) value;
            return emisija.getNazivEmisije();
        }

        return null;
    }
}


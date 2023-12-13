package com.processinformationsystemsui.panel.Gost.ListaGostiju;

import com.processinformationsystemsui.common.list.BaseListCellRenderer;
import com.processinformationsystemsui.model.GostModel;

public class ListaGostijuCellRenderer extends BaseListCellRenderer<GostModel> {

    public ListaGostijuCellRenderer() {
        super();
    }

    @Override
    protected Object setValue() {
        if (value instanceof GostModel gost) {
            return String.format("%s %s", gost.getImeGosta(), gost.getPrezimeGosta());
        }

        return null;
    }
}

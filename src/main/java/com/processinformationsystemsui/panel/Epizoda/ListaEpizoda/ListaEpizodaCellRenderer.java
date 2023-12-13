package com.processinformationsystemsui.panel.Epizoda.ListaEpizoda;

import com.processinformationsystemsui.common.list.BaseListCellRenderer;
import com.processinformationsystemsui.model.EpizodaModel;

public class ListaEpizodaCellRenderer extends BaseListCellRenderer<EpizodaModel> {
    public ListaEpizodaCellRenderer() {
        super();
    }

    @Override
    protected Object setValue() {
        if (value instanceof EpizodaModel) {
            EpizodaModel epizoda = (EpizodaModel) value;
            return epizoda.getNazivEpizode();
        }

        return null;
    }
}


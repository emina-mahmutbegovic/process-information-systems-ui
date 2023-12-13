package com.processinformationsystemsui.panel.Voditelj.ListaVoditelja;

import com.processinformationsystemsui.common.list.BaseListCellRenderer;
import com.processinformationsystemsui.model.VoditeljModel;

public class ListaVoditeljaCellRenderer extends BaseListCellRenderer<VoditeljModel> {
    public ListaVoditeljaCellRenderer() {
        super();
    }

    @Override
    protected Object setValue() {
        if (value instanceof VoditeljModel) {
            VoditeljModel voditelj = (VoditeljModel) value;
            return String.format("%s %s", voditelj.getImeVoditelja(), voditelj.getPrezimeVoditelja());
        }

        return null;
    }
}

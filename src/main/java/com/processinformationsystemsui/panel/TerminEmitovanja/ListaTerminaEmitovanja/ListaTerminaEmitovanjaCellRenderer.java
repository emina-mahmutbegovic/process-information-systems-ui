package com.processinformationsystemsui.panel.TerminEmitovanja.ListaTerminaEmitovanja;

import com.processinformationsystemsui.common.list.BaseListCellRenderer;
import com.processinformationsystemsui.model.TerminEmitovanjaModel;

public class ListaTerminaEmitovanjaCellRenderer extends BaseListCellRenderer<TerminEmitovanjaModel> {
    public ListaTerminaEmitovanjaCellRenderer() {
        super();
    }

    @Override
    protected Object setValue() {
        if (value instanceof TerminEmitovanjaModel) {
            TerminEmitovanjaModel terminEmitovanja = (TerminEmitovanjaModel) value;
            return String.format("%s - %s, %s", terminEmitovanja.getVrijemePocetka(), terminEmitovanja.getVrijemeZavrsetka(), terminEmitovanja.getDatumEmitovanja());
        }

        return null;
    }
}


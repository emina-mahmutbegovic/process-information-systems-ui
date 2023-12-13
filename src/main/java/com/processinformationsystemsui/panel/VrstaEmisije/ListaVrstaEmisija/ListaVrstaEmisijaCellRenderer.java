package com.processinformationsystemsui.panel.VrstaEmisije.ListaVrstaEmisija;

import com.processinformationsystemsui.common.list.BaseListCellRenderer;
import com.processinformationsystemsui.model.VrstaEmisijeModel;

public class ListaVrstaEmisijaCellRenderer extends BaseListCellRenderer<VrstaEmisijeModel> {
    public ListaVrstaEmisijaCellRenderer() {
        super();
    }

    @Override
    protected Object setValue() {
        if (value instanceof VrstaEmisijeModel) {
            VrstaEmisijeModel vrstaEmisije = (VrstaEmisijeModel) value;
            return vrstaEmisije.getNazivVrsteEmisije();
        }

        return null;
    }
}

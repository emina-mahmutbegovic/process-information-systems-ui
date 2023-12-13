package com.processinformationsystemsui.common;

import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.panel.Epizoda.Create.CreateEpizodaModel;
import com.processinformationsystemsui.panel.TerminEmitovanja.Create.CreateTerminEmitovanjaModel;

public interface EmisijaDataChangeListener {
    void onVrstaEmisijeSelected(Object data);
    void onGostSelected(GostModel data);
    void onVoditeljSelected(Object data);
    void onUrednikSelected(Object data);
    void onEpizodaAdded(CreateEpizodaModel data);
}

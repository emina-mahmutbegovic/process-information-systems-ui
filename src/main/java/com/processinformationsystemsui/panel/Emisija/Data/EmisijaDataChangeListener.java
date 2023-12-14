package com.processinformationsystemsui.panel.Emisija.Data;

import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.panel.Epizoda.Create.CreateEpizodaModel;

import java.io.IOException;

public interface EmisijaDataChangeListener {
    void onVrstaEmisijeSelected(Object data);
    void onGostSelected(GostModel data);
    void onVoditeljSelected(Object data);
    void onUrednikSelected(Object data);
    void onEpizodaAdded(CreateEpizodaModel data);
    void onEpizodaDeleted() throws IOException;
    void onGostDeleted() throws IOException;
}

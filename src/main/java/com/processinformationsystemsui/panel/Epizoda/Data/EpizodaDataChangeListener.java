package com.processinformationsystemsui.panel.Epizoda.Data;

import com.processinformationsystemsui.panel.TerminEmitovanja.Create.CreateTerminEmitovanjaModel;

import java.io.IOException;

public interface EpizodaDataChangeListener {
    void onTerminEmitovanjaAdded(CreateTerminEmitovanjaModel data);
    void onTerminEmitovanjaDeleted() throws IOException;
}

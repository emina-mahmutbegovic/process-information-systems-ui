package com.processinformationsystemsui.panel.Emisija.ListaEmisija.Data;

import java.io.IOException;

public interface ListaEmisijaDataChangeListener {
    void onEmisijaCreated() throws IOException;
    void onEmisijaDeleted() throws IOException;
}

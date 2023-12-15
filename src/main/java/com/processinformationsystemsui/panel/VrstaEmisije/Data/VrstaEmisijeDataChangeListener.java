package com.processinformationsystemsui.panel.VrstaEmisije.Data;

import com.processinformationsystemsui.panel.VrstaEmisije.Create.CreateVrstaEmisijeModel;

import java.io.IOException;

public interface VrstaEmisijeDataChangeListener {
    void onVrstaEmisijeCreated(CreateVrstaEmisijeModel data) throws IOException;
    void onVrstaEmisijeEdited() throws IOException;
    void onVrstaEmisijeDeleted() throws IOException;
}

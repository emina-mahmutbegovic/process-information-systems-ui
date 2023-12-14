package com.processinformationsystemsui.panel.VrstaEmisije.Data;

import com.processinformationsystemsui.panel.VrstaEmisije.create.CreateVrstaEmisijeModel;

import java.io.IOException;

public interface VrstaEmisijeDataChangeListener {
    void onVrstaEmisijeCreated(CreateVrstaEmisijeModel data) throws IOException;
}

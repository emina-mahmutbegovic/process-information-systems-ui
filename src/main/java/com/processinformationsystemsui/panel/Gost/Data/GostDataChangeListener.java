package com.processinformationsystemsui.panel.Gost.Data;

import com.processinformationsystemsui.panel.Gost.Create.CreateGostModel;

import java.io.IOException;

public interface GostDataChangeListener {
    void onGostCreated(CreateGostModel data) throws IOException;
    void onGostEdited() throws IOException;
    void onGostDeleted() throws IOException;
}

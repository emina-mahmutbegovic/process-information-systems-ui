package com.processinformationsystemsui.panel.Urednik.Data;

import com.processinformationsystemsui.panel.Urednik.ListaUrednika.Create.CreateUrednikModel;

import java.io.IOException;

public interface UrednikDataChangeListener {
    void onUrednikCreated(CreateUrednikModel data) throws IOException;
    void onUrednikEdited() throws IOException;
    void onUrednikDeleted() throws IOException;
}

package com.processinformationsystemsui.panel.Voditelj.Data;

import com.processinformationsystemsui.panel.Voditelj.Create.CreateVoditeljModel;

import java.io.IOException;

public interface VoditeljiDataChangeListener {
   void onVoditeljCreated(CreateVoditeljModel createVoditeljModel) throws IOException;
   void onVoditeljEdited() throws IOException;
   void onVoditeljDeleted() throws IOException;
}

package com.processinformationsystemsui.panel.Voditelj.Data;

import com.processinformationsystemsui.panel.Voditelj.Create.CreateVoditeljModel;

import java.io.IOException;

public interface VoditeljiDataChangeListener {
   void onVoditeljCreated(CreateVoditeljModel data) throws IOException;
   void onVoditeljEdited() throws IOException;
   void onVoditeljDeleted() throws IOException;
}

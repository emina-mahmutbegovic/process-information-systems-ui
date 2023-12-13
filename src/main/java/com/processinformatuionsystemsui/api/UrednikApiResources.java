package com.processinformatuionsystemsui.api;

import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.model.VoditeljModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class UrednikApiResources extends ApiResources<UrednikModel> {

    private final String URL;

    public UrednikApiResources() {
        super(UrednikModel.class);

        this.URL = ApiResources.BASE_URL + "urednici";
    }

    public List<UrednikModel> getAllUrednici() throws IOException {
        URL url = new URL(this.URL);

        return getAllElements(url);
    }

    public UrednikModel getUrednikById(String idUrednika) throws IOException {
        URL url = new URL(this.URL + "/" + idUrednika);

        return this.getElementById(url);
    }

    public UrednikModel updateUrednik(UrednikModel urednik, String idUrednika) throws IOException {
        URL url = new URL(this.URL + "/" + idUrednika);

        return updateElement(urednik, url);
    }

    public void deleteUrednik(String idUrednika) throws IOException {
        URL url = new URL(this.URL + "/" + idUrednika);

        deleteById(url);
    }
}

package com.processinformatuionsystemsui.api;

import com.processinformationsystemsui.model.EmisijaModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class EmisijaApiResources extends ApiResources<EmisijaModel> {
    private final String URL;

    public EmisijaApiResources() {
        super(EmisijaModel.class);

        this.URL = ApiResources.BASE_URL + "emisije";
    }

    public List<EmisijaModel> getAllEmisija() throws IOException {
        return this.getAllElements(new URL(this.URL));
    }

    public EmisijaModel updateEmisija(EmisijaModel emisija, String idEmisije) throws IOException {
        URL url = new URL(this.URL + "/" + idEmisije);

        return updateElement(emisija, url);
    }

    public void deleteEmisija(String idEmisije) throws IOException {
        URL url = new URL(this.URL + "/" + idEmisije);

        deleteById(url);
    }
}

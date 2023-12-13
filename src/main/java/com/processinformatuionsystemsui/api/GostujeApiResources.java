package com.processinformatuionsystemsui.api;

import com.processinformationsystemsui.model.GostujeModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GostujeApiResources extends ApiResources<GostujeModel> {

    private final String URL;

    public GostujeApiResources() {
        super(GostujeModel.class);

        this.URL = ApiResources.BASE_URL + "gostuje";
    }

    public List<GostujeModel> getAllGostujeByEmisijaId(String idEmisije) throws IOException {
        URL url = new URL(this.URL + "/" + idEmisije);
        return this.getAllElements(url);
    }

    public void deleteGostujeByIdGostaAndIdEmisije(String idGosta, String idEmisije) throws IOException {
        String queryParams = "?idGosta=" + idGosta + "&idEmisije=" + idEmisije;
        URL url = new URL(this.URL + queryParams);

        deleteById(url);
    }
}

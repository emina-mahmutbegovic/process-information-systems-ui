package com.processinformatuionsystemsui.api;

import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.model.VrstaEmisijeModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class VoditeljApiResources extends ApiResources<VoditeljModel> {

    private final String URL;

    public VoditeljApiResources() {
        super(VoditeljModel.class);

        this.URL = ApiResources.BASE_URL + "voditelji";
    }

    public List<VoditeljModel> getAllVoditelji() throws IOException {
        URL url = new URL(this.URL);

        return getAllElements(url);
    }

    public VoditeljModel getVoditeljById(String idVoditelja) throws IOException {
        URL url = new URL(this.URL + "/" + idVoditelja);

        return this.getElementById(url);
    }

    public VoditeljModel updateVoditelj(VoditeljModel voditelj, String idVoditelja) throws IOException {
        URL url = new URL(this.URL + "/" + idVoditelja);

        return updateElement(voditelj, url);
    }

    public void deleteVoditelj(String idVoditelja) throws IOException {
        URL url = new URL(this.URL + "/" + idVoditelja);

        deleteById(url);
    }
}

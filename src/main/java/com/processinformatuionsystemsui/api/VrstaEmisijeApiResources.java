package com.processinformatuionsystemsui.api;

import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.model.VrstaEmisijeModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class VrstaEmisijeApiResources extends ApiResources<VrstaEmisijeModel> {

    private final String URL;

    public VrstaEmisijeApiResources() {
        super(VrstaEmisijeModel.class);

        this.URL = ApiResources.BASE_URL + "vrsta-emisije";
    }

    public List<VrstaEmisijeModel> getAllVrstaEmisije() throws IOException {
        URL url = new URL(this.URL);

        return this.getAllElements(url);
    }

    public VrstaEmisijeModel getVrstaEmisijeById(String idVrsteEmisije) throws IOException {
        URL url = new URL(this.URL + "/" + idVrsteEmisije);

        return this.getElementById(url);
    }

    public VrstaEmisijeModel updateVrstaEmisijeModel(VrstaEmisijeModel vrstaEmisije, String idVrsteEmisije) throws IOException {
        URL url = new URL(this.URL + "/" + idVrsteEmisije);

        return updateElement(vrstaEmisije, url);
    }

    public void deleteVrstaEmisije(String idVrsteEmisije) throws IOException {
        URL url = new URL(this.URL + "/" + idVrsteEmisije);

        deleteById(url);
    }
}

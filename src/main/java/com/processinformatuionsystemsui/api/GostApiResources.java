package com.processinformatuionsystemsui.api;

import com.processinformationsystemsui.model.GostModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class GostApiResources extends ApiResources<GostModel> {

    private final String URL;

    public GostApiResources() {
        super(GostModel.class);

        this.URL = ApiResources.BASE_URL + "gosti";
    }

    public List<GostModel> getAllGosti(Set<String> ids) throws IOException {
        StringBuilder parameterString = new StringBuilder();
        if(ids.isEmpty()) {
            parameterString.append("idGostiju=");
        } else {
            for (String id : ids) {
                if (parameterString.length() > 0) {
                    parameterString.append("&");
                }
                parameterString.append("idGostiju=").append(id);
            }
        }

        URL url = new URL(URL + "?" + parameterString);

        return getAllElements(url);
    }

    public GostModel updateGost(GostModel gost, String idGosta) throws IOException {
        URL url = new URL(this.URL + "/" + idGosta);

        return updateElement(gost, url);
    }

    public void deleteGost(String idGosta) throws IOException {
        URL url = new URL(this.URL + "/" + idGosta);

        deleteById(url);
    }
}

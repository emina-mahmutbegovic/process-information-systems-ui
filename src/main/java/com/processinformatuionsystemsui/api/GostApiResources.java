package com.processinformatuionsystemsui.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.panel.Gost.Create.CreateGostModel;
import com.processinformationsystemsui.panel.Urednik.ListaUrednika.Create.CreateUrednikModel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class GostApiResources extends ApiResources<GostModel> {

    private final String URL;

    public GostApiResources() {
        super(GostModel.class);

        this.URL = ApiResources.BASE_URL + "gosti";
    }

    public GostModel createGost(CreateGostModel newGost) throws IOException {
        URL url = new URL(this.URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Serialize the element to JSON (You may need to adjust this part based on your needs)
            ObjectMapper objectMapper = new ObjectMapper();
            String elementJson = objectMapper.writeValueAsString(newGost);

            // Write the serialized element to the request body
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(elementJson.getBytes());
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                // Handle a successful response, if necessary
                return parseResponse(connection);
            } else {
                throw new IOException("HTTP error code: " + connection.getResponseCode());
            }
        } catch (Exception ex) {
            throw new IOException("Could not create new gost.", ex);
        } finally {
            connection.disconnect();
        }
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

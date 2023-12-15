package com.processinformatuionsystemsui.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.processinformationsystemsui.model.VrstaEmisijeModel;
import com.processinformationsystemsui.panel.VrstaEmisije.Create.CreateVrstaEmisijeModel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class VrstaEmisijeApiResources extends ApiResources<VrstaEmisijeModel> {

    private final String URL;

    public VrstaEmisijeApiResources() {
        super(VrstaEmisijeModel.class);

        this.URL = ApiResources.BASE_URL + "vrsta-emisije";
    }

    public VrstaEmisijeModel createVrstaEmisije(CreateVrstaEmisijeModel newVrstaEmisije) throws IOException {
        URL url = new URL(this.URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Serialize the element to JSON (You may need to adjust this part based on your needs)
            ObjectMapper objectMapper = new ObjectMapper();
            String elementJson = objectMapper.writeValueAsString(newVrstaEmisije);

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
            throw new IOException("Could not create new episode.", ex);
        } finally {
            connection.disconnect();
        }

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

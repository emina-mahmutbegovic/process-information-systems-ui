package com.processinformatuionsystemsui.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.processinformationsystemsui.model.EmisijaModel;
import com.processinformationsystemsui.panel.Emisija.Create.CreateEmisijaModel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class EmisijaApiResources extends ApiResources<EmisijaModel> {
    private final String URL;

    public EmisijaApiResources() {
        super(EmisijaModel.class);

        this.URL = ApiResources.BASE_URL + "emisije";
    }

    public EmisijaModel createEmisija(CreateEmisijaModel newEmisija) throws IOException {
        URL url = new URL(this.URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Serialize the element to JSON (You may need to adjust this part based on your needs)
            ObjectMapper objectMapper = new ObjectMapper();
            String elementJson = objectMapper.writeValueAsString(newEmisija);

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
            throw new IOException("Could not create new emisija.", ex);
        } finally {
            connection.disconnect();
        }

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

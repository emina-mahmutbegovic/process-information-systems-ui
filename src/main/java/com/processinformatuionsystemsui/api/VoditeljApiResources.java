package com.processinformatuionsystemsui.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.panel.Voditelj.Create.CreateVoditeljModel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class VoditeljApiResources extends ApiResources<VoditeljModel> {

    private final String URL;

    public VoditeljApiResources() {
        super(VoditeljModel.class);

        this.URL = ApiResources.BASE_URL + "voditelji";
    }

    public VoditeljModel createVoditelj(CreateVoditeljModel newVoditelj) throws IOException {
        URL url = new URL(this.URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Serialize the element to JSON (You may need to adjust this part based on your needs)
            ObjectMapper objectMapper = new ObjectMapper();
            String elementJson = objectMapper.writeValueAsString(newVoditelj);

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
            throw new IOException("Could not create new voditelj.", ex);
        } finally {
            connection.disconnect();
        }
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

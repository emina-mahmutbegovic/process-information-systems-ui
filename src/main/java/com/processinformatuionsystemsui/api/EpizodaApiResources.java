package com.processinformatuionsystemsui.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.processinformationsystemsui.model.EpizodaModel;
import com.processinformationsystemsui.panel.Epizoda.Create.CreateEpizodaModel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class EpizodaApiResources extends ApiResources<EpizodaModel> {
    private final String URL;

    public EpizodaApiResources() {
        super(EpizodaModel.class);

        this.URL = ApiResources.BASE_URL + "epizode";
    }

    public EpizodaModel createEpizoda(CreateEpizodaModel newEpizoda) throws IOException {
        URL url = new URL(this.URL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try {
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");

                // Serialize the element to JSON (You may need to adjust this part based on your needs)
                ObjectMapper objectMapper = new ObjectMapper();
                String elementJson = objectMapper.writeValueAsString(newEpizoda);

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

    public EpizodaModel getEpizodaById(String idEpizode) throws IOException {
        URL url = new URL(this.URL + "/" + idEpizode);

        return this.getElementById(url);
    }

    public List<EpizodaModel> getAllEpizode(String idEmisije) throws IOException {
        String URL_WITH_QUERY = this.URL + "?idEmisije="+idEmisije;

        return this.getAllElements(new URL(URL_WITH_QUERY));
    }

    public void deleteEpizoda(String idEpizode) throws IOException {
        URL url = new URL(this.URL + "/" + idEpizode);

        deleteById(url);
    }
}

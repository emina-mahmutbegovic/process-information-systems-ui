package com.processinformatuionsystemsui.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.processinformationsystemsui.model.GostModel;
import com.processinformationsystemsui.model.UrednikModel;
import com.processinformationsystemsui.model.VoditeljModel;
import com.processinformationsystemsui.panel.Urednik.ListaUrednika.Create.CreateUrednikModel;
import com.processinformationsystemsui.panel.Voditelj.Create.CreateVoditeljModel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UrednikApiResources extends ApiResources<UrednikModel> {

    private final String URL;

    public UrednikApiResources() {
        super(UrednikModel.class);

        this.URL = ApiResources.BASE_URL + "urednici";
    }

    public UrednikModel createUrednik(CreateUrednikModel newUrednik) throws IOException {
        URL url = new URL(this.URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Serialize the element to JSON (You may need to adjust this part based on your needs)
            ObjectMapper objectMapper = new ObjectMapper();
            String elementJson = objectMapper.writeValueAsString(newUrednik);

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

    public List<UrednikModel> getAllUrednici() throws IOException {
        URL url = new URL(this.URL);

        return getAllElements(url);
    }

    public UrednikModel getUrednikById(String idUrednika) throws IOException {
        URL url = new URL(this.URL + "/" + idUrednika);

        return this.getElementById(url);
    }

    public UrednikModel updateUrednik(UrednikModel urednik, String idUrednika) throws IOException {
        URL url = new URL(this.URL + "/" + idUrednika);

        return updateElement(urednik, url);
    }

    public void deleteUrednik(String idUrednika) throws IOException {
        URL url = new URL(this.URL + "/" + idUrednika);

        deleteById(url);
    }
}

package com.processinformatuionsystemsui.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.processinformationsystemsui.model.EmisijaModel;
import com.processinformationsystemsui.model.EpizodaModel;
import com.processinformationsystemsui.model.TerminEmitovanjaModel;
import com.processinformationsystemsui.panel.Epizoda.Create.CreateEpizodaModel;
import com.processinformationsystemsui.panel.TerminEmitovanja.Create.CreateTerminEmitovanjaModel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TerminEmitovanjaApiResources extends ApiResources<TerminEmitovanjaModel> {
    private final String URL;

    public TerminEmitovanjaApiResources() {
        super(TerminEmitovanjaModel.class);

        this.URL = ApiResources.BASE_URL + "termini-emitovanja";
    }

    public TerminEmitovanjaModel createTerminEmitovanja(CreateTerminEmitovanjaModel newTerminEmitovanja) throws IOException {
        URL url = new URL(this.URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Serialize the element to JSON (You may need to adjust this part based on your needs)
            ObjectMapper objectMapper = new ObjectMapper();
            String elementJson = objectMapper.writeValueAsString(newTerminEmitovanja);

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
            throw new IOException("Could not create new termin emitovanja.", ex);
        } finally {
            connection.disconnect();
        }

    }

    public List<TerminEmitovanjaModel> getAllTerminiByIdEpizode(String idEpizode) throws IOException {
        URL url = new URL(this.URL + "/" + idEpizode);

        return getAllElements(url);
    }

    public void deleteTerminEmitovanja(String deletionQuery) throws IOException {
        URL url = new URL(this.URL + deletionQuery);

        deleteById(url);
    }
}

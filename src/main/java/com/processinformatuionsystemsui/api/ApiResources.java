package com.processinformatuionsystemsui.api;

import com.processinformationsystemsui.util.JsonArrayParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiResources<T> {
    private final Class<T> type;
    private static final String BASE_URL = "http://localhost:8080/api/";
    private final String URL;

    public ApiResources(Class<T> type, String url) {
        this.type = type;
        this.URL = BASE_URL + url;
    }

    public List<T> getAllElements() throws IOException {
        URL url = new URL(URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }

                in.close();

                // Parse the response and return the list of T objects
                JsonArrayParser<T> jsonArrayParser = new JsonArrayParser<>(type);
                return jsonArrayParser.parse(response.toString());
            } else {
                throw new IOException("HTTP error code: " + connection.getResponseCode());
            }
        } catch (Exception ex) {
            throw new IOException(String.format("Could not get list of: %s", type.getName()), ex);
        } finally {
            connection.disconnect();
        }
    }
}

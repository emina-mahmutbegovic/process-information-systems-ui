package com.processinformatuionsystemsui.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.processinformatuionsystemsui.api.util.JsonArrayParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiResources<T> {
    private final Class<T> type;
    protected static final String BASE_URL = "http://localhost:8080/api/";

    protected ApiResources(Class<T> type) {
        this.type = type;
    }

    protected List<T> getAllElements(URL url) throws IOException {
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

    protected T getElementById(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return parseResponse(connection);
            } else {
                throw new IOException("HTTP error code: " + connection.getResponseCode());
            }
        } catch (Exception ex) {
            throw new IOException(String.format("Could not get list of: %s", type.getName()), ex);
        } finally {
            connection.disconnect();
        }
    }

    protected T updateElement(T element, URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            // Serialize the element to JSON (You may need to adjust this part based on your needs)
            ObjectMapper objectMapper = new ObjectMapper();
            String elementJson = objectMapper.writeValueAsString(element);

            // Write the serialized element to the request body
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(elementJson.getBytes());
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Handle a successful response, if necessary
                return parseResponse(connection);
            } else {
                throw new IOException("HTTP error code: " + connection.getResponseCode());
            }
        } catch (Exception ex) {
            throw new IOException(String.format("Could not update: %s", type.getName()), ex);
        } finally {
            connection.disconnect();
        }
    }

    T parseResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line);
        }

        in.close();

        // Parse the response and return the list of T objects
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.toString(), type);
    }

    protected void deleteById(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("DELETE");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) {
                System.out.println("Deletion successful!");
            } else {
                throw new IOException("HTTP error code: " + connection.getResponseCode());
            }
        } catch (Exception ex) {
            throw new IOException(String.format("Could not delete %s for endpoint: %s", type.getName(), url.toString()), ex);
        } finally {
            connection.disconnect();
        }
    }
}

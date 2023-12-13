package com.processinformatuionsystemsui.api.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class JsonArrayParser<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> type;

    public JsonArrayParser(Class<T> type) {
        this.type = type;
    }

    public List<T> parse(String jsonArrayString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(String.valueOf(jsonArrayString));

            if (jsonNode.isArray()) {
                List<T> objects = new ArrayList<>();
                Iterator<JsonNode> elements = jsonNode.elements();

                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    T object = objectMapper.treeToValue(element, type);
                    objects.add(object);
                }
                return objects;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}

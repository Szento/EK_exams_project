package app.api;

import app.exceptions.APIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIReader {

    private static final Logger logger = LoggerFactory.getLogger(APIReader.class);

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final HttpClient client = HttpClient.newHttpClient();

    public String readAPI(String url, String apiKey) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.warn("GET {} failed with status {}", url, response.statusCode());
                throw new APIException(response.statusCode(), "GET request failed for " + url);
            }
            return response.body();
        } catch (IOException e) {
            throw new APIException(502, "Could not reach API: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new APIException(502, "API request was interrupted");
        }
    }

    public <T> T convertFromJson(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new APIException(500, "Could not parse JSON: " + e.getOriginalMessage());
        }
    }

    public <T> T getWithJacksonGeneric(String url, Class<T> tClass, String apiKey) {
        String json = readAPI(url, apiKey);
        return convertFromJson(json, tClass);
    }
}
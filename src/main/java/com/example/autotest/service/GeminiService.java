package com.example.autotest.service;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GeminiService {


    public static final String API_KEY = "AIzaSyCmwSac0O5LfsHTjoUDaN--SvDF7XhA2po";


    @SneakyThrows
    public String callAI(String prompt, String testInstruction) {
        String jsonBody = buildBody(prompt, testInstruction);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=" + API_KEY))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return extractJsonResponse(response.body());
        } else {
            return String.valueOf(response.statusCode());
        }
    }

    public static String extractJsonResponse(String jsonResponse) {
        JSONObject rootObject = new JSONObject(jsonResponse);

        JSONArray candidatesArray = rootObject.getJSONArray("candidates");
        JSONObject firstCandidate = candidatesArray.getJSONObject(0);

        JSONObject contentObject = firstCandidate.getJSONObject("content");
        JSONArray partsArray = contentObject.getJSONArray("parts");
        JSONObject firstPart = partsArray.getJSONObject(0);

        String extractedJson = firstPart.getString("text");
        return extractedJson;
    }

    private static String buildBody(String prompt, String jsonContent) {
        JSONObject body = new JSONObject();
        JSONArray contents = new JSONArray();
        JSONObject part = new JSONObject();
        part.put("text", prompt);
        JSONArray jsonArray  = new JSONArray();
        jsonArray.put(part);
        jsonArray.put(new JSONObject().put("text", jsonContent));
        contents.put(new JSONObject().put("parts",jsonArray));
        body.put("contents", contents);
        return body.toString();
    }
}

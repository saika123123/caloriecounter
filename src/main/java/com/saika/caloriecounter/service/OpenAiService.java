package com.theokanning.openai.service;

import java.io.IOException;

import com.google.gson.Gson;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OpenAiService {

    private final String API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private final OkHttpClient httpClient = new OkHttpClient();
    private final Gson gson = new Gson();

    public CompletionResponse createCompletion(CompletionRequest completionRequest) {
        String json = gson.toJson(completionRequest);
        RequestBody body = RequestBody.create(json, okhttp3.MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + completionRequest.getToken())
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseBody = response.body().string();
            return gson.fromJson(responseBody, CompletionResponse.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
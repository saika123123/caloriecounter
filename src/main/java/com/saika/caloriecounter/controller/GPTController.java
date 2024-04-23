package com.saika.caloriecounter.controller;

import java.util.ResourceBundle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;






@RestController
@RequestMapping("/gpt")
public class GPTController {

    private final OpenAiService openAiService;

    public GPTController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/get-response")
    public ResponseEntity<String> getResponse(@RequestBody String foodName) {
        ResourceBundle config = ResourceBundle.getBundle("prop");
        String prompt = "食べ物: " + foodName + " のおおよそのカロリー(Kcal)はいくつですか？";
        String token = config.getString("openai.token");
        String model = config.getString("openai.model");
        int maxTokens = Integer.valueOf(config.getString("openai.maxTokens"));
        Double temperature = Double.valueOf(config.getString("openai.temperature"));

        CompletionRequest completionRequest = CompletionRequest.builder()
        .prompt(prompt)
        .model(model)
        .echo(true)
        .maxTokens(maxTokens)
        .temperature(temperature)
        .build();

        CompletionChoice choice = openAiService.createCompletion(completionRequest).getChoices().get(0);
        return ResponseEntity.ok(choice.getText());
    }
}

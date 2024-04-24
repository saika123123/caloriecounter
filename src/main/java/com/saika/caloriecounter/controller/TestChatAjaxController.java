package com.saika.caloriecounter.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/gpt")
public class TestChatAjaxController {

	@RequestMapping("/gpt")
	public String toStart() {
		return "start";
	}

	@PostMapping("/openAiChat")
	public ResponseEntity<String> openAiChat(@RequestBody String preMessage) {
		String url = "https://api.openai.com/v1/chat/completions";
		String api_key = System.getenv("sk-proj-mfPQe7nmLEZYuaWfZ2mNT3BlbkFJiXPjQ4gp4wBcBl8lZJ8w");
		String model = "gpt-3.5-turbo";
		String message = "{\"role\": \"system\", \"content\": \"返答は日本語で\"},{\"role\": \"user\", \"content\": \" + preMessage + \"}";
		String content = "";
		try {
			// HTTPリクエストの作成
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", "Bearer " + api_key);
			con.setDoOutput(true);

			// リクエストの送信
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			out.write("{\"model\": \"" + model + "\", \"messages\": [" + message + "]}");
			out.close();

			// レスポンスの取得
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			Gson gson = new Gson();
			JsonObject jsonResponse = gson.fromJson(in, JsonObject.class);
			JsonArray choicesArray = jsonResponse.getAsJsonArray("choices");
			JsonObject messageObject = choicesArray.get(0).getAsJsonObject().get("message").getAsJsonObject();
			content = messageObject.get("content").getAsString();

			// レスポンスの出力
			System.out.println(content);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseEntity<>(content, HttpStatus.OK);
	}
}

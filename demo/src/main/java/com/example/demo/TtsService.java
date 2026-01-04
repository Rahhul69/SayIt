package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class TtsService {

    @Value("${elevenlabs.api.key}")
    private String apiKey;

    private final String VOICE_ID = "pNInz6obpgDQGcFmaJgB"; // Adam
    private final String URL = "https://api.elevenlabs.io/v1/text-to-speech/" + VOICE_ID;

    public byte[] generateAudio(String text) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("xi-api-key", apiKey);

            String cleanText = text.replace("\"", "").replace("\n", " ");

            // The working JSON format
            String jsonBody = String.format(
                    "{\"text\": \"%s\", \"model_id\": \"eleven_turbo_v2\", \"voice_settings\": {\"stability\": 0.5, \"similarity_boost\": 0.5}}",
                    cleanText
            );

            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
            ResponseEntity<byte[]> response = restTemplate.postForEntity(URL, requestEntity, byte[].class);

            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
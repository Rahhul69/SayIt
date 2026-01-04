package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Critical: Allows your HTML file to call this API
public class TtsController {

    @Autowired
    private TtsService ttsService;

    @PostMapping("/speak")
    public ResponseEntity<byte[]> speak(@RequestBody String text) {
        // Call the service
        byte[] audioData = ttsService.generateAudio(text);

        // Return the audio file to the browser
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                .body(audioData);
    }
}
package br.com.app.ia.spring_ia.controller;

import br.com.app.ia.spring_ia.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerativeAiController {

    private final ChatService service;

    public GenerativeAiController(ChatService service) {
        this.service = service;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return service.getResponse(prompt);
    }
}

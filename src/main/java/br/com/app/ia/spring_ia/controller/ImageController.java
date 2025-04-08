package br.com.app.ia.spring_ia.controller;

import br.com.app.ia.spring_ia.service.ImageService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {

    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @GetMapping("generate-image")
    public List<String> generateImage(@RequestParam String prompt,
                              @RequestParam(defaultValue = "hd") String quality,
                              @RequestParam(defaultValue = "1")Integer n,
                              @RequestParam(defaultValue = "1024")Integer height,
                              @RequestParam(defaultValue = "1024")Integer width) {
        ImageResponse response = service.generateImage(prompt, quality, n, height, width);

        return response.getResults().stream().map(result -> result.getOutput().getUrl()).toList();
    }
}

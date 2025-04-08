package br.com.app.ia.spring_ia.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final OpenAiImageModel imageModel;

    public ImageService(OpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public ImageResponse generateImage(String prompt, String quality, Integer n, Integer height, Integer width) {
        return imageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .quality(quality)
                                .N(n)
                                .height(height)
                                .width(width)
                                .build())
        );
        //ImageResponse imageResponse = imageModel.call(new ImagePrompt(prompt)); // Forma simples de fazer a geração de imagens
        //return imageResponse;

        // Forma mais complexa
    }
}

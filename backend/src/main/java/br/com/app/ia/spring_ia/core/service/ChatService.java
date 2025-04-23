package br.com.app.ia.spring_ia.core.service;

import br.com.app.ia.spring_ia.utils.FileUtils;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {

        this.chatModel = chatModel;
    }

    public String getResponse(String prompt) {
        String resposta = chatModel.call(prompt);
        FileUtils.salvarEmArquivo(resposta, "chat_simples"); // ou qualquer outro nome descritivo
        return resposta;
    }

    public String getResponseWithOptions(String prompt) {
        ChatResponse response = chatModel.call(
                new Prompt(
                        prompt,
                        OpenAiChatOptions.builder()
                                .model("gpt-4o")
                                .temperature(0.4)
                                .build()
                ));
        String resposta = response.getResult().getOutput().getText();
        FileUtils.salvarEmArquivo(resposta, "chat_options"); // nome descritivo
        return resposta;
    }
}

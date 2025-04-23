package br.com.app.ia.spring_ia.core.service;

import br.com.app.ia.spring_ia.utils.FileUtils;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class RecipeService {

    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients, String cuisine, String dietaryRestrictions) {
        var template = """
                
                Quero criar uma receita usando os seguintes ingredientes: {ingredients}
                O tipo de culinária que prefiro é {cuisine}.
                Por favor, considere as seguintes restrições alimentares: {dietaryRestrictions}.
                Por favor, envie-me uma receita detalhada, incluindo título, lista de ingredientes e instruções de preparo.
                
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "ingredients", ingredients,
                "cuisine", cuisine,
                "dietaryRestrictions", dietaryRestrictions
                );

        Prompt prompt = promptTemplate.create(params);
        String response = chatModel.call(prompt).getResult().getOutput().getText();

        String conteudoFinal = "Prompt enviado:\n" + prompt.getContents() +
                "\n\nResposta recebida:\n" + response;


        FileUtils.salvarEmArquivo(response, "receita");

        return response;
    }
}

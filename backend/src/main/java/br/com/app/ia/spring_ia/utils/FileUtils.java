package br.com.app.ia.spring_ia.itils;

import org.springframework.ai.chat.model.ChatResponse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static void saveResponse(ChatResponse responseApi, String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(texto))) {
            writer.write(String.valueOf(responseApi));
            System.out.println("Arquivo salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}

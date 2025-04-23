package br.com.app.ia.spring_ia.utils;

import org.springframework.ai.chat.model.ChatResponse;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtils {

    private static final String BASE_DIRETORIO = "respostas";

    public static void salvarEmArquivo(String conteudo, String subpasta) {
        // Cria a pasta de destino com base no nome do serviço
        File pasta = new File(BASE_DIRETORIO + File.separator + subpasta);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String nomeArquivo = "resposta_" + timestamp + ".txt";
        File arquivo = new File(pasta, nomeArquivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write(conteudo);
            System.out.println("Resposta salva em: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao salvar resposta: " + e.getMessage());
        }
    }

//    public static void saveImage(byte[] imagemBytes, String subpasta) {
//        // Cria a pasta de destino para a imagem
//        File pasta = new File(BASE_DIRETORIO + File.separator + subpasta);
//        if (!pasta.exists()) {
//            pasta.mkdirs();
//        }
//
//        String timestamp = LocalDateTime.now()
//                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
//        String nomeArquivoImagem = "imagem_" + timestamp + ".png"; // ou .jpg dependendo do tipo de imagem
//        File arquivoImagem = new File(pasta, nomeArquivoImagem);
//
//        try (FileOutputStream fos = new FileOutputStream(arquivoImagem)) {
//            fos.write(imagemBytes);
//            System.out.println("Imagem salva em: " + arquivoImagem.getAbsolutePath());
//        } catch (IOException e) {
//            System.err.println("Erro ao salvar imagem: " + e.getMessage());
//        }
//    }
}

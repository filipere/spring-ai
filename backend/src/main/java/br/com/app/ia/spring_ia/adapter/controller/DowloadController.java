package br.com.app.ia.spring_ia.adapter.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/ai/download")
public class DowloadController {

    @GetMapping("/{servico}")
    public ResponseEntity<Resource> downloadArquivoMaisRecente(@PathVariable String servico) throws IOException {

        Path pasta = Paths.get("respostas", servico);

        if (!Files.exists(pasta)) {
            return ResponseEntity.notFound().build();
        }

        Path arquivoMaisRecente = Files.list(pasta)
                .filter(Files::isRegularFile)
                .max((p1, p2) -> Long.compare(p1.toFile().lastModified(), p2.toFile().lastModified())) // Comparando o timestamp de modificação
                .orElseThrow(() -> new IOException("Nenhum arquivo encontrado"));

        Resource resource = new UrlResource(arquivoMaisRecente.toUri());
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivoMaisRecente.getFileName() + "\"")
                .body(resource);
    }
}
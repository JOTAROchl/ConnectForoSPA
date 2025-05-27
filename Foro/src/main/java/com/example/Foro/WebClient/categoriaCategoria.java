package com.example.Foro.WebClient;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class categoriaCategoria {
    private final WebClient webClient;

    public categoriaCategoria(@Value("${categorias.service.url}") String categoriaServidor){
        this.webClient = WebClient.builder().baseUrl(categoriaServidor).build();
    }

    public Map<String, Object> obtenerCategoriaPorId(Long id){
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException("Categoria no encontrada"))))
            .bodyToMono(Map.class)
            .block();
    }
}

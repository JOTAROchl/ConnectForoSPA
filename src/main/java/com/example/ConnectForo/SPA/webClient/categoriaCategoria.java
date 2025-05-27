package com.example.ConnectForo.SPA.webClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class categoriaCategoria {
private final WebClient webClient;

    public categoriaCategoria(@Value("${categoria.service.url}") String categoriaServidor){
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

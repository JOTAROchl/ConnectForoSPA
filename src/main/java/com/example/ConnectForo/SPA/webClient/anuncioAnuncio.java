package com.example.ConnectForo.SPA.webClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class anuncioAnuncio {
private final WebClient webClient;

    public anuncioAnuncio(@Value("${anuncio.service.url}") String anuncioServidor){
        this.webClient = WebClient.builder().baseUrl(anuncioServidor).build();
    }

    public Map<String, Object> obtenerAnuncioPorId(Long id){
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException("Anuncio no encontrado"))))
            .bodyToMono(Map.class)
            .block();
    }
}

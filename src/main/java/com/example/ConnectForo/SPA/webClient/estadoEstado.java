package com.example.ConnectForo.SPA.webClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class estadoEstado {
    private final WebClient webClient;

    public estadoEstado(@Value("${estado.service.url}") String estadoServidor){
        this.webClient = WebClient.builder().baseUrl(estadoServidor).build();
    }

    public Map<String, Object> obtenerEstadoPorId(Long id){
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException("Estado no encontrado"))))
            .bodyToMono(Map.class)
            .block();
    }
}

package com.example.ConnectForo.SPA.webClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class interaccionInteraccion {
private final WebClient webClient;

    public interaccionInteraccion(@Value("${interaccion.service.url}") String interaccionServidor){
        this.webClient = WebClient.builder().baseUrl(interaccionServidor).build();
    }

    public Map<String, Object> obtenerInteraccionPorId(Long id){
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException("Interaccion no encontrada"))))
            .bodyToMono(Map.class)
            .block();
    }
}

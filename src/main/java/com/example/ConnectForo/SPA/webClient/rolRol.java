package com.example.ConnectForo.SPA.webClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class rolRol {
    private final WebClient webClient;

    public rolRol(@Value("${rol.service.url}") String rolServidor){
        this.webClient = WebClient.builder().baseUrl(rolServidor).build();
    }

    public Map<String, Object> obtenerRolPorId(Long id){
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException("Rol no encontrado"))))
            .bodyToMono(Map.class)
            .block();
    }
}

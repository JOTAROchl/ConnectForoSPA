package com.example.ConnectForo.SPA.webClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class usuarioUsuario {
private final WebClient webClient;

    public usuarioUsuario(@Value("${usuario.service.url}") String usuarioServidor){
        this.webClient = WebClient.builder().baseUrl(usuarioServidor).build();
    }

    public Map<String, Object> obtenerUsuarioPorId(Long id){
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException("Usuario no encontrado"))))
            .bodyToMono(Map.class)
            .block();
    }
}

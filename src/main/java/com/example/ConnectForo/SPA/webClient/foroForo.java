package com.example.ConnectForo.SPA.webClient;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class foroForo {
    private final WebClient webClient;

    public foroForo(@Value("${foro.service.url}") String foroServidor){
        this.webClient = WebClient.builder().baseUrl(foroServidor).build();
    }

    public Map<String, Object> obtenerForoPorId(Long id){
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException("Foro no encontrado"))))
            .bodyToMono(Map.class)
            .block();
    }
}


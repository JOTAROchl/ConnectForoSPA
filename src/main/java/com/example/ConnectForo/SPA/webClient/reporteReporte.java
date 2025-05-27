package com.example.ConnectForo.SPA.webClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class reporteReporte {
private final WebClient webClient;

    public reporteReporte(@Value("${reporte.service.url}") String reporteServidor){
        this.webClient = WebClient.builder().baseUrl(reporteServidor).build();
    }

    public Map<String, Object> obtenerReportePorId(Long id){
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new RuntimeException("Reporte no encontrado"))))
            .bodyToMono(Map.class)
            .block();
    }
}

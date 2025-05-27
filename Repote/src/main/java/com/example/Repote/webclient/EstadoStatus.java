package com.example.Repote.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class EstadoStatus {
    private final WebClient webClient;

    public  EstadoStatus(@Value("${estado-service.url}") String estadoServidor ){
        this.webClient = WebClient.builder().baseUrl(estadoServidor).build();
    }

    //metrodo para comunicarme con el microservicio de roles
    //rol y verifica si existe
    //devuelve una estructura de mapa que represemta el json
    public Map<String, Object> obtenerEstadoId(Long id){
        //realiza un consulta HTTP de tipo get al microservicio
        return this.webClient.get()
        .uri("/{id}", id)
        .retrieve()
        .onStatus(status -> status.is4xxClientError(),
        response -> response.bodyToMono(String.class)
        .map(body -> new RuntimeException("Estado no encontrado"))).bodyToMono(Map.class).block();
    }
}

package com.example.Interaccion.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UsuarioUser {
    private final WebClient webClient;

    public  UsuarioUser(@Value("${usuario-service.url}") String usuarioServidor ){
        this.webClient = WebClient.builder().baseUrl(usuarioServidor).build();
    }

    public Map<String, Object> obtenerUsuarioId(Long id){
        //realiza un consulta HTTP de tipo get al microservicio
        return this.webClient.get()
        .uri("/{id}", id)
        .retrieve()
        .onStatus(status -> status.is4xxClientError(),
        response -> response.bodyToMono(String.class)
        .map(body -> new RuntimeException("Usuario no encontrado"))).bodyToMono(Map.class).block();
    }
}

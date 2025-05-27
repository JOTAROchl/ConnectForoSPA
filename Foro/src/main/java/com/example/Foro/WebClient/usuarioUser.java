package com.example.Foro.WebClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class usuarioUser {
    private final WebClient webClient;
    
    public  usuarioUser(@Value("${estado-service.url}") String usuarioServidor ){
        this.webClient = WebClient.builder().baseUrl(usuarioServidor).build();
    }

    //metrodo para comunicarme con el microservicio de usuarios
    //usuario y verifica si existe
    //devuelve una estructura de mapa que representa el json
    public Map<String, Object> obtenerEstadoId(Long id){
        //realiza un consulta HTTP de tipo get al microservicio
        return this.webClient.get()
        .uri("/{id}", id)
        .retrieve()
        .onStatus(status -> status.is4xxClientError(),
        response -> response.bodyToMono(String.class)
        .map(body -> new RuntimeException("Usuario no encontrado"))).bodyToMono(Map.class).block();
    }
}

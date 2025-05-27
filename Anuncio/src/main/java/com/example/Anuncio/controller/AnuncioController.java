package com.example.Anuncio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Anuncio.model.Anuncio;
import com.example.Anuncio.service.AnuncioService;

@RestController
@RequestMapping("/api/v1/anuncios")
public class AnuncioController {
    @Autowired 
    private AnuncioService anuncioService;
    //EndPoints = EndP

    //EndP para todos anuncios
    @GetMapping
    public ResponseEntity<List<Anuncio>> obtenerAnuncios(){
        List<Anuncio> anuncios = anuncioService.getAnuncios();
        if(anuncios.isEmpty()){
            //si esta vacia = 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(anuncios);
    }

    //EndP para anuncio id
    @GetMapping("/{id}")
    public ResponseEntity<Anuncio> obtenerAnuncioId(@PathVariable Long id){
        try {
            Anuncio anuncio = anuncioService.getAnuncioId(id);
            return ResponseEntity.ok(anuncio);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
            //si no existe = 404
        } 
    }

    //EndP para guardar 
    @PostMapping
    public ResponseEntity<Anuncio> guardarAnuncio(@RequestBody Anuncio anuncio){
        return ResponseEntity.status(201).body(anuncioService.saveAnuncio(anuncio));
    }
    //EndP para actualizar 
    @PutMapping("/{id}")
    public ResponseEntity<Anuncio> actualizarAnuncio(@PathVariable Long id, @RequestBody Anuncio anuncio){
        try {
            Anuncio anuncio2 = anuncioService.updateAnuncio(id, anuncio);
            return ResponseEntity.ok(anuncio2);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    //EndP para eliminar 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAnuncio(@PathVariable Long id){
        try {
            anuncioService.deleteAnuncio(id);
            return ResponseEntity.noContent().build(); 
            //204 si fue correcto
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); 
            //404 si no existia
        }
    }


}

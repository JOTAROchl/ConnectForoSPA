package com.example.Interaccion.controller;

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

import com.example.Interaccion.model.Interaccion;
import com.example.Interaccion.service.InteraccionService;

@RestController
@RequestMapping("/api/v1/interacciones")
public class InteraccioonController {
    @Autowired
    private InteraccionService interaccionService;
    //EndPoints = EndP

    //EndP para todos interacciones
    @GetMapping
    public ResponseEntity<List<Interaccion>> obtenerInteraccion(){
        List<Interaccion> interaccion = interaccionService.getInteraccion();
        if(interaccion.isEmpty()){
            //si esta vacia = 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(interaccion);
    }   

    //EndP para interaccion id
    @GetMapping("/{id}")
    public ResponseEntity<Interaccion> obtenerInteraccionId(@PathVariable Long id){
        try {
            Interaccion interaccion = interaccionService.getInteraccionId(id);
            return ResponseEntity.ok(interaccion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
            //si no existe = 404
        } 
    }

    //EndP para guardar 
    @PostMapping
    public ResponseEntity<Interaccion> guardarInteraccion(@RequestBody Interaccion interaccion){
        
        return ResponseEntity.status(201).body(interaccionService.saveInteraccion(interaccion));
    }

    //EndP para actualizar 
    @PutMapping("/{id}")
    public ResponseEntity<Interaccion> actualizarReporte(@PathVariable Long id, @RequestBody Interaccion interaccion){
        try {
            Interaccion interaccion2 = interaccionService.updateInteraccion(id, interaccion);
            return ResponseEntity.ok(interaccion2);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //EndP para eliminar 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInteraccion(@PathVariable Long id){
        try {
            interaccionService.deleteInteraccion(id);
            return ResponseEntity.noContent().build(); 
            //204 si fue correcto
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); 
            //404 si no existia
        }
    }
}

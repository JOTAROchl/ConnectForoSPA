package com.example.Estado.controller;

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

import com.example.Estado.model.Estado;
import com.example.Estado.service.EstadoService;

@RestController
@RequestMapping("/api/v1/estado")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;

    //EndP para todos estados

    @GetMapping
    public ResponseEntity<List<Estado>> obtenerEstados(){
        List<Estado> estados = estadoService.getEstados();
        if (estados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estados);
    }

    //EndP para solo un estado
    @GetMapping({"/{id}"})
    public ResponseEntity<Estado> obtenerEstadoId(@PathVariable Long id){
        try{
            Estado estado =  estadoService.getEstado(id);
            return ResponseEntity.ok(estado);

        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //EndP para guardar un estado
    @PostMapping
    public ResponseEntity<Estado> guardarEstado(@RequestBody Estado estado){
        return ResponseEntity.status(201).body(estadoService.saveEstado(estado));
    }

    //EndP para actualizar un estado
    @PutMapping("/{id}")
    public ResponseEntity<Estado> actualizarEstado(@PathVariable Long id, @RequestBody Estado estado){
        try{
            Estado estado2 = estadoService.updateEstado(id, estado);
            return ResponseEntity.ok(estado2);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //EndP para eliminar un estado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id){
        try {
            estadoService.deleteEstado(id);
                return ResponseEntity.noContent().build();
                // 204 si esta bien    
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
            // 404 si no existe
        }
    }



}

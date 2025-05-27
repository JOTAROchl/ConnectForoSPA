package com.example.Repote.controller;

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

import com.example.Repote.model.Reporte;
import com.example.Repote.service.ReporteService;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {
    @Autowired 
    private ReporteService reporteService;
    //EndPoints = EndP

    //EndP para todos reportes
    @GetMapping
    public ResponseEntity<List<Reporte>> obtenerReportes(){
        List<Reporte> reportes = reporteService.getReporte();
        if(reportes.isEmpty()){
            //si esta vacia = 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reportes);
    }

    //EndP para reporte id
    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerReporteId(@PathVariable Long id){
        try {
            Reporte reporte = reporteService.getReporteId(id);
            return ResponseEntity.ok(reporte);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
            //si no existe = 404
        } 
    }

    //EndP para guardar 
    @PostMapping
    public ResponseEntity<Reporte> guardarReporte(@RequestBody Reporte reporte){
        return ResponseEntity.status(201).body(reporteService.saveReporte(reporte));
    }

    //EndP para actualizar 
    @PutMapping("/{id}")
    public ResponseEntity<Reporte> actualizarReporte(@PathVariable Long id, @RequestBody Reporte reporte){
        try {
            Reporte reporte2 = reporteService.updateReporte(id, reporte);
            return ResponseEntity.ok(reporte2);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //EndP para eliminar 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id){
        try {
            reporteService.deleteReporte(id);
            return ResponseEntity.noContent().build(); 
            //204 si fue correcto
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); 
            //404 si no existia
        }
    }

}

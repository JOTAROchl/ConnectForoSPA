package com.example.Foro.Controller;

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

import com.example.Foro.Model.Foro;
import com.example.Foro.Services.ForoServices;

@RestController
@RequestMapping("/api/v1/foro")
public class ForoController {
    @Autowired
    private ForoServices foroServices;

    //endpoint para obtener todos los foros
    @GetMapping
    public ResponseEntity<List<Foro>> obtenerForo(){
        List<Foro> foro = foroServices.getAllForos();
        if (foro.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(foro);
    }

    //endpoint para obtener un foro por su id
    @GetMapping("/{id}")
    public ResponseEntity<Foro> obtenerForoPorId(@PathVariable Long id){
        try{
            Foro foro = foroServices.getForo(id);
            return ResponseEntity.ok(foro);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }        
    }

    //endpoint para agregar un foro
    @PostMapping
    public ResponseEntity<Foro> guardarForo(@RequestBody Foro nuevo){
        return ResponseEntity.status(201).body(foroServices.saveForo(nuevo));
    }

    //endpoint para actualizar un foro con su id
    @PutMapping("/{id}")
    public ResponseEntity<Foro> actualizarForo(@PathVariable Long id, @RequestBody Foro foro) {
        try{
            Foro foroActualizado = foroServices.updateForo(id, foro);
            return ResponseEntity.ok(foroActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //endpoint para eliminar un foro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarForo(@PathVariable Long id){
        try{
            foroServices.deleteForo(id); // llama al servicio para eliminar la categoria por id
            return ResponseEntity.noContent().build();// devolverá el codigo 204 (No Content) si la categoria se elimina correctamente
        } catch (Exception e){
            return ResponseEntity.notFound().build(); // si no encuentra la categoria, devolverá el codigo 404 (Not Found)
        }
    }
}

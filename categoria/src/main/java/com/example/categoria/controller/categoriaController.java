package com.example.categoria.controller;

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

import com.example.categoria.model.categoria;
import com.example.categoria.services.categoriaServices;

@RestController
@RequestMapping("/api/v1/categorias")

public class categoriaController {
    @Autowired
    private categoriaServices categoriaServices;

    //endpoint para obtener todas las categorias
    @GetMapping
    public ResponseEntity<List<categoria>> obtenerCategorias() {
        List<categoria> categoria = categoriaServices.getCategoria();
        if(categoria.isEmpty()){ // verifica si la lista de categorias está vacía o no
            return ResponseEntity.noContent().build(); // si está vacía, devolverá el codigo 204 (No Content)
        }
        return ResponseEntity.ok(categoria); // si no está vacía, devolverá el codigo 200 (OK) con todas las listas de las categorias
    }

    //endpoint para obtener una categoria por su id
    @GetMapping("/{id}")
    public ResponseEntity<categoria> obtenerCategoriaPorId(@PathVariable Long id){
        try{
            categoria categoria = categoriaServices.getCategoria(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
            // si no encuentra la categoria, devolverá el codigo 404 (Not Found)
        }
    }

    //endpoint para guardar una categoria
    @PostMapping
    public ResponseEntity<categoria> guardarCategoria(@RequestBody categoria categoria){
        return ResponseEntity.status(201).body(categoriaServices.saveCategoria(categoria));// devolverá el codigo 201 (Created) con la categoria creada
    }

    //endpoint para actualizar una categoria
    @PutMapping("/{id}")
    public ResponseEntity<categoria> actualizarCategoria(@PathVariable Long id, @RequestBody categoria categoria){
        try{
            categoria categoriaActualizada = categoriaServices.updateCategoria(id, categoria);
            return ResponseEntity.ok(categoriaActualizada); // devolverá el codigo 200 (OK) con la categoria actualizada
        } catch (Exception e){
            return ResponseEntity.notFound().build(); // si no encuentra la categoria, devolverá el codigo 404 (Not Found)
        }
    }

    //endpoint para eliminar una categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id){
        try{
            categoriaServices.deleteCategoria(id); // llama al servicio para eliminar la categoria por id
            return ResponseEntity.noContent().build();// devolverá el codigo 204 (No Content) si la categoria se elimina correctamente
        } catch (Exception e){
            return ResponseEntity.notFound().build(); // si no encuentra la categoria, devolverá el codigo 404 (Not Found)
        }
    }
}

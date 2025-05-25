package com.example.Rol.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Rol.model.Rol;
import com.example.Rol.service.RolService;

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


@RestController
@RequestMapping("/api/v1/roles")

public class RolController {
    @Autowired
    private RolService rolService;

    //endpoint para obtener todos los roles
    @GetMapping
    public ResponseEntity<List<Rol>> obtenerRoles() {
        List<Rol> roles = rolService.getRoles();
        if(roles.isEmpty()){ //verifica si 
            //la lista de roles esta vacia
            return ResponseEntity.noContent().build();
            //vacia = 204
        }
        return ResponseEntity.ok(roles);
    } 
    
    //endpoint para obtener un rol por id
    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable Long id) {
            try{
                Rol rol = rolService.getRol(id);
                return ResponseEntity.ok(rol);
            } catch (Exception e){
                return ResponseEntity.notFound().build();
                //si no lo encuentra = 404
            }
    }

    @PostMapping
    public ResponseEntity<Rol> guardarRol(@RequestBody Rol rol) {
        return ResponseEntity.status(201).body(rolService.saveRol(rol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRolId(@PathVariable Long id, @RequestBody Rol rol){
        try {
            Rol rolActualizado = rolService.updateRol(id, rol);
            return ResponseEntity.ok(rolActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        } 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        try {
            rolService.deleteRol(id);
            return ResponseEntity.noContent().build(); 
            //genera error 204 si fue correcto
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); 
            //404 si no existe
        }
    }



}

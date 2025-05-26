package com.example.Usuario.controller;

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

import com.example.Usuario.model.Usuario;
import com.example.Usuario.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired 
    private UsuarioService usuarioService;
    //EndPoints = EndP


    //EndP para todos usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios(){
        List<Usuario> usuarios = usuarioService.getUsuarios();
        if(usuarios.isEmpty()){
            //si esta vacia = 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    //EndP para usuario id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioId(@PathVariable Long id){
        try {
            Usuario usuario = usuarioService.getUsuarioId(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
            //si no existe = 404
        } 
    }

    //EndP para guardar
    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(201).body(usuarioService.saveUsuario(usuario));
    }

    //EndP para actuallizar
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        try {
            Usuario usuario2 = usuarioService.updateUsuario(id, usuario);
            return ResponseEntity.ok(usuario2);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //EndP para eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build(); 
            //204 si fue correcto
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); 
            //404 si no existia
        }
    }
}

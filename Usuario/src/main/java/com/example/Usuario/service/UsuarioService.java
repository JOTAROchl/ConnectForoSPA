package com.example.Usuario.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Usuario.model.Usuario;
import com.example.Usuario.repository.UsuarioRepository;
import com.example.Usuario.webclient.EstadoStatus;
import com.example.Usuario.webclient.RolRole;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRole rolRole;
    @Autowired
    private EstadoStatus estadoStatus;

    //MTD obtener todos
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    //MTD obtener por id
    public Usuario getUsuarioId(Long id) {
        return usuarioRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
    }

    //MTD guardar usuario
    public Usuario saveUsuario(Usuario usuario) {
        //valida si existe el rol
        Map<String, Object> rol = rolRole.obtenerRolId(usuario.getRolId());
        if(rol == null || rol.isEmpty()) {
            throw new RuntimeException("Rol no encontrado, no se puede agregar usuario");
        }
        //valida si existe el estado
        Map<String, Object> Estado = estadoStatus.obtenerEstadoId(usuario.getEstadoId());
        if (Estado == null || Estado.isEmpty()) {
            throw new RuntimeException("Estado no encontrado, no se puede agregar usuario");
        }

        return usuarioRepository.save(usuario);
    }

    //MTD actualizar usuario
    public Usuario updateUsuario(Long id, Usuario usuarioDetalle) {
        //verifica si el usuario existe
        Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
    
        //actualiza
        usuario.setNombreUsuario(usuarioDetalle.getNombreUsuario());
        usuario.setClave(usuarioDetalle.getClave());
        usuario.setCorreo(usuarioDetalle.getCorreo());
        usuario.setRolId(usuarioDetalle.getRolId());
        usuario.setEstadoId(usuarioDetalle.getEstadoId());
        //guarda
        return usuarioRepository.save(usuario);
    }
    //MTD eliminar usuario
    public void deleteUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id)
        //si no lo encuentra, se cancela
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        //extermina el usuario
        usuarioRepository.delete(usuario);
    }
    
    //MTD para obtner por el rol
    public Usuario getUsuarioRol(Long rolId){
        return usuarioRepository.findById(rolId)
        .orElseThrow(() -> new RuntimeException("Usuario con rol no encontrado"));

    }



}

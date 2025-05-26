package com.example.Interaccion.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Interaccion.model.Interaccion;
import com.example.Interaccion.repository.InteraccionRepository;
import com.example.Interaccion.webclient.UsuarioUser;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class InteraccionService {
    @Autowired
    private InteraccionRepository interaccionRepository;
    @Autowired
    private UsuarioUser usuarioUser;

    //MTD para obtener todos
    public List<Interaccion> getInteraccion(){
        return interaccionRepository.findAll();
    }

    //MTD para uno por id
    public Interaccion getInteraccionId(Long id){
        return interaccionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Interaccion no encontrado"));
    }
    
    //MTD para guardar
    public Interaccion saveInteraccion(Interaccion interaccion){
        Map<String, Object> Usuario = usuarioUser.obtenerUsuarioId(interaccion.getUsuarioId());
        if (Usuario == null || Usuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado, no se puede agregar Interaccion");
        }
        return interaccionRepository.save(interaccion);
    }

    //MTD para actualizar
    public Interaccion updateInteraccion(Long id, Interaccion interaccionDetalle) {
        //verifica si el usuario existe
        Interaccion interaccion = interaccionRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Interaccion no encontrado"));
    
        //actualiza
        interaccion.setFecInteraccion(interaccionDetalle.getFecInteraccion());
        interaccion.setTipoInteraccion(interaccionDetalle.getTipoInteraccion());
        interaccion.setTipoElemento(interaccionDetalle.getTipoElemento());
        interaccion.setUsuarioId(interaccionDetalle.getUsuarioId());
        //guarda
        return interaccionRepository.save(interaccion);
    }

    //MTD para eliminar
    public void deleteInteraccion(Long id){
        Interaccion interaccion = interaccionRepository.findById(id)
        //si no lo encuentra, se cancela
        .orElseThrow(() -> new RuntimeException("Interaccion no encontrado"));
        //extermina el usuario
        interaccionRepository.delete(interaccion);
    }
}

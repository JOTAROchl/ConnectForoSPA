package com.example.Anuncio.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Anuncio.model.Anuncio;
import com.example.Anuncio.repository.AnuncioRepository;
import com.example.Anuncio.webclient.EstadoStatus;
import com.example.Anuncio.webclient.UsuarioUser;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AnuncioService {
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private UsuarioUser usuarioUser;
    @Autowired
    private EstadoStatus estadoStatus;

    //MTD para obtener todos
    public List<Anuncio> getAnuncios(){
        return anuncioRepository.findAll();
    }

    //MTD para uno por id
    public Anuncio getAnuncioId(Long id){
        return anuncioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Anuncio no encontrado"));
    }

    //MTD para guardar
    public Anuncio saveAnuncio(Anuncio anuncio) {
        //valida si existe el estado
        Map<String, Object> Estado = estadoStatus.obtenerEstadoId(anuncio.getEstadoId());
        if (Estado == null || Estado.isEmpty()) {
            throw new RuntimeException("Estado no encontrado, no se puede agregar anuncio");
        }
        //valida si existe el usuario
        Map<String, Object> Usuario = usuarioUser.obtenerUsuarioId(anuncio.getUsuarioId());
        if (Usuario == null || Usuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado, no se puede agregar anuncio");
        }

        return anuncioRepository.save(anuncio);
    }
    
    //MTD para actualizar
    public Anuncio updateAnuncio(Long id, Anuncio anuncioDetalle) {
        //verifica si el usuario existe
        Anuncio anuncio = anuncioRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Anuncio no encontrado"));
    
        //actualiza
        anuncio.setTitulo(anuncioDetalle.getTitulo());
        anuncio.setFecPublicacion(anuncioDetalle.getFecPublicacion());
        anuncio.setDescripcion(anuncioDetalle.getDescripcion()); 
        anuncio.setLugar(anuncioDetalle.getLugar());
        anuncio.setPrioridad(anuncioDetalle.getPrioridad());
        anuncio.setEstadoId(anuncioDetalle.getEstadoId());
        anuncio.setUsuarioId(anuncioDetalle.getUsuarioId());
        //guarda
        return anuncioRepository.save(anuncio);
    }
    //MTD para eliminar
    public void deleteAnuncio(Long id){
        Anuncio anuncio = anuncioRepository.findById(id)
        //si no lo encuentra, se cancela
        .orElseThrow(() -> new RuntimeException("Anuncio no encontrado"));
        //extermina el usuario
        anuncioRepository.delete(anuncio);
    }

}

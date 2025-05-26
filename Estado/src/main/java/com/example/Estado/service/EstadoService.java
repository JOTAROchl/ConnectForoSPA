package com.example.Estado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Estado.model.Estado;
import com.example.Estado.repository.EstadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    //MTD obtener los tipos de Estado
    public List<Estado> getEstados() {
        return estadoRepository.findAll();
    }

    //MTD estao por id
    public Estado getEstado(Long id){
        return estadoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Estado no encontrado"));
    }

    //MTD ppara crear/guardar Estado
    public Estado saveEstado(Estado estado){
        return estadoRepository.save(estado);
    }

    //MTD para actualizar
    public Estado updateEstado(Long id, Estado estado){
        Estado estado2 = estadoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Estado no encontrado"));
        //actualizando
        estado2.setNombreEstado(estado.getNombreEstado());
        //guardando
        return estadoRepository.save(estado2);
    }

    //MTD para delete
    public void deleteEstado(Long id){
        Estado estado = estadoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Estado no encontrado"));
        estadoRepository.delete(estado);
    }
}

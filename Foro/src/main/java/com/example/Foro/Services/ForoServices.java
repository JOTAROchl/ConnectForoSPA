package com.example.Foro.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Foro.Model.Foro;
import com.example.Foro.Repository.ForoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ForoServices {
    @Autowired
    private ForoRepository foroRepository;
    
    //Metodo para obtener todos los foros
    public List<Foro> getAllForos() {
        return foroRepository.findAll();
    }

    //Metodo para obtener un foro por su id
    public Foro getForo(Long id) {
        return foroRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Foro no encontrado"));
    }

    //Metodo para crear un foro un foro
    public Foro saveForo(Foro foro) {
        return foroRepository.save(foro);
    }

    //Metodo para actualizar un foro
    public Foro updateForo(long id, Foro foro) {
        return foroRepository.save(foro);
    }

    //Metodo para eliminar un foro por su id
    public void deleteForo(long id) {
        foroRepository.deleteById(id);
    }

}

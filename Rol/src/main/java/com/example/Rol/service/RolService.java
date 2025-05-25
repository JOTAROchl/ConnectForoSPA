package com.example.Rol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Rol.model.Rol;
import com.example.Rol.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    //MTD obtener todos roles
    public List<Rol> getRoles(){
        return rolRepository.findAll();
    }

    //MTD rol especifico por id
    public Rol getRol(Long id){
        return rolRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Rol no encontrado"));
    }

    //MTD para crear rol
    public Rol saveRol (Rol rol){
        return rolRepository.save(rol);
    }

    //MTD para actualizar rol
    public Rol updateRol(Long id, Rol rolDetails){
        Rol rol = rolRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Rol no encontrado"));
        //actualiza
        rol.setNombreRol(rolDetails.getNombreRol()); 
        //guarda
        return rolRepository.save(rol);
    }

    //MTD para eliminar rol
    public void deleteRol(Long id){
        Rol rol = rolRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Rol no encontrado"));
        rolRepository.delete(rol);
    }
}

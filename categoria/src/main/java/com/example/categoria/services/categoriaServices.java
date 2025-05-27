package com.example.categoria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.categoria.model.categoria;
import com.example.categoria.repository.categoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class categoriaServices{
    @Autowired
    private categoriaRepository categoriaRepository;

    //metodo para obtener todos las catgorias
    public List<categoria> getCategoria(){
        return categoriaRepository.findAll();
    }

    //metodo para obtener una categoria por id
    public categoria getCategoria(Long id) {
        return categoriaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    //metodo para crear una categoria
    public categoria saveCategoria(categoria categoria){
        return categoriaRepository.save(categoria);
    }

    //metodo para actualizar una categoria
    public categoria updateCategoria(Long id, categoria categoriaDetails){
        categoria categoria = categoriaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        //actualiza
        categoria.setNombreCategoria(categoriaDetails.getNombreCategoria());
        //guarda
        return categoriaRepository.save(categoria);
    }

    //metodo para eliminar una categoria
    public void deleteCategoria(long id){
        categoria categoria = categoriaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        categoriaRepository.delete(categoria);
    }
}

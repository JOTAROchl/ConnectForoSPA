package com.example.Usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{

}

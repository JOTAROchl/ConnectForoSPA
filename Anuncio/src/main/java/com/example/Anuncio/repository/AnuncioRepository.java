package com.example.Anuncio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Anuncio.model.Anuncio;

public interface AnuncioRepository extends JpaRepository <Anuncio, Long>{

}

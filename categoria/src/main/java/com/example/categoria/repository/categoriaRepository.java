package com.example.categoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.categoria.model.categoria;

@Repository
public interface categoriaRepository extends JpaRepository<categoria, Long> {

}

package com.example.Anuncio.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "anuncios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Column(name = "fecha_publicacion", nullable = false)
    private Date fecPublicacion;

    @Column(nullable = false, length = 30)
    private String prioridad;

    @Column(nullable = false, length = 50)
    private String lugar;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Long estadoId;

    @Column(nullable = false)
    private Long usuarioId;
}

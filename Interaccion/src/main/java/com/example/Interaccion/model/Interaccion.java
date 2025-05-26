package com.example.Interaccion.model;

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
@Table(name = "interaccion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_interaccion", nullable = false)
    private Date fecInteraccion;

    @Column(nullable = false, length = 50)
    private String tipoInteraccion;

    @Column(nullable = false, length = 50)
    private String tipoElemento;

    @Column(nullable = false)
    private Long usuarioId;

}

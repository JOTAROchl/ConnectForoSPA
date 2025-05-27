package com.example.Repote.model;

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
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_reporte", nullable = false)
    private Date fecReporte;

    @Column(nullable = false, length = 30)
    private String tipoElemento;

    @Column(nullable = false, length = 500)
    private String motivo;

    @Column(nullable = true, length = 500)
    private String respuesta;

    @Column(nullable = true, length = 300)
    private String accion;

    @Column(name = "fecha_accion", nullable = true)
    private Date fecAccion;

    @Column(nullable = false)
    private Long usuarioId; //usuario reportado

    @Column(nullable = false)
    private Long estadoId;
}

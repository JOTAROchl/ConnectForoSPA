package com.example.Foro.Model;


import java.util.Date;

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
@Table(name = "foro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Foro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_foro")
    private Long idForo;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(name="fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name="fecha_baneo", nullable = true)
    private Date fechaBaneo;

    @Column(nullable = true, length = 250)
    private String razonBaneo;

    //@Column(nullable=false)
    //private int idUsuario;
}

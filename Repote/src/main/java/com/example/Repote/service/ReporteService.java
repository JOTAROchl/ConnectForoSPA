package com.example.Repote.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repote.model.Reporte;
import com.example.Repote.repository.ReporteRepository;
import com.example.Repote.webclient.EstadoStatus;
import com.example.Repote.webclient.UsuarioUser;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;
    @Autowired
    private UsuarioUser usuarioUser;
    @Autowired
    private EstadoStatus estadoStatus;

    //MTD para obtener todos
    public List<Reporte> getReporte(){
        return reporteRepository.findAll();
    }

    //MTD para uno por id
    public Reporte getReporteId(Long id){
        return reporteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
    }

    //MTD para guardar
    public Reporte saveReporte(Reporte reporte) {
        //valida si existe el estado
        Map<String, Object> Estado = estadoStatus.obtenerEstadoId(reporte.getEstadoId());
        if (Estado == null || Estado.isEmpty()) {
            throw new RuntimeException("Estado no encontrado, no se puede agregar reporte");
        }
        //valida si existe el usuario
        Map<String, Object> Usuario = usuarioUser.obtenerUsuarioId(reporte.getUsuarioId());
        if (Usuario == null || Usuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado, no se puede agregar reporte");
        }

        return reporteRepository.save(reporte);
    }

    //MTD para actualizar
    public Reporte updateReporte(Long id, Reporte reporteDetalle) {
        //verifica si el usuario existe
        Reporte reporte = reporteRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Reporte no encontrado"));
    
        //actualiza
        reporte.setFecReporte(reporteDetalle.getFecReporte());
        reporte.setTipoElemento(reporteDetalle.getTipoElemento());     
        reporte.setMotivo(reporteDetalle.getMotivo());
        reporte.setRespuesta(reporteDetalle.getRespuesta());
        reporte.setAccion(reporteDetalle.getAccion());
        reporte.setFecAccion(reporteDetalle.getFecAccion());
        reporte.setUsuarioId(reporteDetalle.getUsuarioId());
        reporte.setEstadoId(reporteDetalle.getEstadoId());
        //guarda
        return reporteRepository.save(reporte);
    }

    //MTD para eliminar
    public void deleteReporte(Long id){
        Reporte reporte = reporteRepository.findById(id)
        //si no lo encuentra, se cancela
        .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        //extermina el usuario
        reporteRepository.delete(reporte);
    }
}

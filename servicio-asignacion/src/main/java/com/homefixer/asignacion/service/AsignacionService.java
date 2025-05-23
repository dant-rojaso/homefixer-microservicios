package com.homefixer.asignacion.service;

import com.homefixer.asignacion.model.Asignacion;
import com.homefixer.asignacion.model.TecnicoDisponible;
import com.homefixer.asignacion.repository.AsignacionRepository;
import com.homefixer.asignacion.repository.TecnicoDisponibleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsignacionService {
    
    private final AsignacionRepository asignacionRepository;
    private final TecnicoDisponibleRepository tecnicoDisponibleRepository;
    
    public TecnicoDisponible registrarTecnico(TecnicoDisponible tecnico) {
        tecnico.setDisponible(true);
        tecnico.setSolicitudesAsignadas(0);
        tecnico.setCalificacionPromedio(5.0);
        return tecnicoDisponibleRepository.save(tecnico);
    }
    
    public List<TecnicoDisponible> obtenerTecnicosDisponibles() {
        return tecnicoDisponibleRepository.findByDisponibleTrue();
    }
    
    public Asignacion crearAsignacionManual(Long solicitudId, Long tecnicoId) {
        // Verificar que el técnico existe y está disponible
        TecnicoDisponible tecnico = tecnicoDisponibleRepository.findByTecnicoId(tecnicoId)
            .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));
        
        if (!tecnico.getDisponible()) {
            throw new RuntimeException("Técnico no disponible");
        }
        
        // Crear la asignación
        Asignacion asignacion = new Asignacion();
        asignacion.setSolicitudId(solicitudId);
        asignacion.setTecnicoId(tecnicoId);
        asignacion.setDistanciaKm(5.0); // Valor simulado
        asignacion.setTiempoEstimadoMinutos(15); // Valor simulado
        
        Asignacion nuevaAsignacion = asignacionRepository.save(asignacion);
        
        // Actualizar disponibilidad del técnico
        tecnico.setDisponible(false);
        tecnico.setSolicitudesAsignadas(tecnico.getSolicitudesAsignadas() + 1);
        tecnicoDisponibleRepository.save(tecnico);
        
        return nuevaAsignacion;
    }
    
    public Asignacion actualizarEstadoAsignacion(Long asignacionId, String nuevoEstado) {
        Asignacion asignacion = asignacionRepository.findById(asignacionId)
            .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
        
        asignacion.setEstado(nuevoEstado);
        Asignacion asignacionActualizada = asignacionRepository.save(asignacion);
        
        // Si se completa o rechaza, liberar al técnico
        if ("COMPLETADA".equals(nuevoEstado) || "RECHAZADA".equals(nuevoEstado)) {
            TecnicoDisponible tecnico = tecnicoDisponibleRepository
                .findByTecnicoId(asignacion.getTecnicoId())
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));
            
            tecnico.setDisponible(true);
            tecnicoDisponibleRepository.save(tecnico);
        }
        
        return asignacionActualizada;
    }
    
    public List<Asignacion> obtenerAsignacionesPorTecnico(Long tecnicoId) {
        return asignacionRepository.findByTecnicoId(tecnicoId);
    }
    
    public Asignacion obtenerAsignacionPorSolicitud(Long solicitudId) {
        return asignacionRepository.findBySolicitudId(solicitudId)
            .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
    }
}
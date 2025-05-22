package com.homefixer.solicitudes.service;

import com.homefixer.solicitudes.model.Solicitud;
import com.homefixer.solicitudes.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Marca esta clase como un servicio Spring
@RequiredArgsConstructor // Lombok: genera constructor con campos final
public class SolicitudService {
    
    // Inyección de dependencias mediante constructor (gracias a @RequiredArgsConstructor)
    private final SolicitudRepository solicitudRepository;
    
    public Solicitud crearSolicitud(Solicitud solicitud) {
        // Guarda la solicitud en la BD
        Solicitud nuevaSolicitud = solicitudRepository.save(solicitud);
        
        
        return nuevaSolicitud;
    }
    
    public Solicitud obtenerSolicitudPorId(Long id) {
        return solicitudRepository.findById(id)        // findById devuelve un Optional 
            .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));        //orElseThrow lanza excepción si no existe
    }
    
    public List<Solicitud> obtenerSolicitudesPorCliente(Long clienteId) {
        return solicitudRepository.findByClienteId(clienteId);
    }
    
    public List<Solicitud> obtenerSolicitudesPorTecnico(Long tecnicoId) {
        return solicitudRepository.findByTecnicoId(tecnicoId);
    }
    
    public List<Solicitud> obtenerSolicitudesPorEstado(String estado) {
        return solicitudRepository.findByEstado(estado);
    }
    
    public Solicitud actualizarEstado(Long id, String nuevoEstado) {

        Solicitud solicitud = obtenerSolicitudPorId(id);        // Primero busca la solicitud
        
        solicitud.setEstado(nuevoEstado);        // Actualiza el estado
        
        Solicitud solicitudActualizada = solicitudRepository.save(solicitud);        // Guarda los cambios
        
        return solicitudActualizada;
    }
    
    public void eliminarSolicitud(Long id) {
        solicitudRepository.deleteById(id);
    }
}
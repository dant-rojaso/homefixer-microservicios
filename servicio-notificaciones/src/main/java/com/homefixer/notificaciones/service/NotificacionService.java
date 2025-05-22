package com.homefixer.notificaciones.service;

import com.homefixer.notificaciones.model.Notificacion;
import com.homefixer.notificaciones.repository.NotificacionRepository;
import com.homefixer.notificaciones.dto.NotificacionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {
    
    private final NotificacionRepository notificacionRepository;
    
    public Notificacion crearNotificacion(NotificacionRequestDTO requestDTO) {
        Notificacion notificacion = new Notificacion();
        notificacion.setClienteId(requestDTO.getClienteId());
        notificacion.setSolicitudId(requestDTO.getSolicitudId());
        notificacion.setTecnicoId(requestDTO.getTecnicoId());
        notificacion.setTipo(requestDTO.getTipo());
        notificacion.setMensaje(requestDTO.getMensaje());
        notificacion.setEstadoAnterior(requestDTO.getEstadoAnterior());
        notificacion.setEstadoNuevo(requestDTO.getEstadoNuevo());
        
        return notificacionRepository.save(notificacion);
    }
    
    public List<Notificacion> obtenerNotificacionesPorCliente(Long clienteId) {
        return notificacionRepository.findByClienteId(clienteId);
    }
    
    public List<Notificacion> obtenerNotificacionesNoLeidasPorCliente(Long clienteId) {
        return notificacionRepository.findByClienteIdAndLeida(clienteId, false);
    }
    
    public List<Notificacion> obtenerNotificacionesPorSolicitud(Long solicitudId) {
        return notificacionRepository.findBySolicitudId(solicitudId);
    }
    
    public List<Notificacion> obtenerNotificacionesPorTecnico(Long tecnicoId) {
        return notificacionRepository.findByTecnicoId(tecnicoId);
    }
    
    public List<Notificacion> obtenerUltimasNotificacionesPorCliente(Long clienteId) {
        return notificacionRepository.findTop5ByClienteIdOrderByFechaCreacionDesc(clienteId);
    }
    
    public Notificacion marcarComoLeida(Long notificacionId) {
        Notificacion notificacion = notificacionRepository.findById(notificacionId)
            .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        
        notificacion.setLeida(true);
        return notificacionRepository.save(notificacion);
    }
    
    public void marcarTodasComoLeidas(Long clienteId) {
        List<Notificacion> notificaciones = notificacionRepository.findByClienteIdAndLeida(clienteId, false);
        
        for (Notificacion notificacion : notificaciones) {
            notificacion.setLeida(true);
            notificacionRepository.save(notificacion);
        }
    }
    
    public Notificacion obtenerNotificacionPorId(Long id) {
        return notificacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
    }
    
    public void eliminarNotificacion(Long id) {
        notificacionRepository.deleteById(id);
    }
}
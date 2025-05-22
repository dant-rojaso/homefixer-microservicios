package com.homefixer.notificaciones.repository;

import com.homefixer.notificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    
    List<Notificacion> findByClienteId(Long clienteId);
    
    List<Notificacion> findByClienteIdAndLeida(Long clienteId, Boolean leida);
    
    List<Notificacion> findBySolicitudId(Long solicitudId);
    
    List<Notificacion> findByTecnicoId(Long tecnicoId);
    
    List<Notificacion> findByTipo(String tipo);
    
    List<Notificacion> findTop5ByClienteIdOrderByFechaCreacionDesc(Long clienteId);
}
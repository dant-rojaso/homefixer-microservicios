package com.homefixer.solicitudes.repository;

import com.homefixer.solicitudes.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository // Marca esta interfaz como un repositorio Spring
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    // JpaRepository proporciona métodos CRUD básicos automáticamente
    
    // Métodos de consulta, JPA genera la implementación
    List<Solicitud> findByClienteId(Long clienteId);
    
    List<Solicitud> findByTecnicoId(Long tecnicoId);
    
    List<Solicitud> findByEstado(String estado);
    
    List<Solicitud> findByClienteIdAndEstado(Long clienteId, String estado);
}
package com.homefixer.asignacion.repository;

import com.homefixer.asignacion.model.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    
    Optional<Asignacion> findBySolicitudId(Long solicitudId);
    
    List<Asignacion> findByTecnicoId(Long tecnicoId);
    
    List<Asignacion> findByEstado(String estado);
}
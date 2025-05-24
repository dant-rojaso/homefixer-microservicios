package com.homefixer.usuarios.repository;

import com.homefixer.usuarios.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    Optional<Cliente> findByEmail(String email);
    
    List<Cliente> findByActivo(Boolean activo);
    
    List<Cliente> findByHistorialServiciosGreaterThan(Integer minServicios);
    
    List<Cliente> findByCalificacionPromedioGreaterThanEqual(Double minCalificacion);
    
    boolean existsByEmail(String email);
}
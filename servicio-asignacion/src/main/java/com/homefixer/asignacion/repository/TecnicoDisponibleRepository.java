package com.homefixer.asignacion.repository;

import com.homefixer.asignacion.model.TecnicoDisponible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TecnicoDisponibleRepository extends JpaRepository<TecnicoDisponible, Long> {
    
    Optional<TecnicoDisponible> findByTecnicoId(Long tecnicoId);
    
    List<TecnicoDisponible> findByDisponibleTrue();
    
    @Query("SELECT t FROM TecnicoDisponible t WHERE t.disponible = true AND t.especialidad = ?1")
    List<TecnicoDisponible> findDisponiblesPorEspecialidad(String especialidad);
}
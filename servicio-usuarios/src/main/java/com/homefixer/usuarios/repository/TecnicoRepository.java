package com.homefixer.usuarios.repository;

import com.homefixer.usuarios.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
    
    Optional<Tecnico> findByEmail(String email);
    
    List<Tecnico> findByActivo(Boolean activo);
    
    List<Tecnico> findByActivoAndDisponible(Boolean activo, Boolean disponible);
    
    @Query("SELECT t FROM Tecnico t WHERE t.especialidades LIKE %?1% AND t.activo = true")
    List<Tecnico> findByEspecialidadesContaining(String especialidad);
    
    @Query("SELECT t FROM Tecnico t WHERE t.activo = true AND t.disponible = true AND t.especialidades LIKE %?1%")
    List<Tecnico> findTecnicosDisponiblesPorEspecialidad(String especialidad);
    
    List<Tecnico> findByCalificacionPromedioGreaterThanEqualOrderByCalificacionPromedioDesc(Double minCalificacion);
    
    @Query("SELECT t FROM Tecnico t WHERE t.activo = true ORDER BY t.calificacionPromedio DESC")
    List<Tecnico> findTecnicosMejorValorados();
    
    List<Tecnico> findByServiciosCompletadosGreaterThanEqual(Integer minServicios);
    
    boolean existsByEmail(String email);
}
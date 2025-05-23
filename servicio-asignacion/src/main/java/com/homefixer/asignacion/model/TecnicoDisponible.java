package com.homefixer.asignacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tecnicos_disponibles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoDisponible {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private Long tecnicoId;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String telefono;
    
    private String especialidad;
    
    @Column(nullable = false)
    private Double latitud;
    
    @Column(nullable = false)
    private Double longitud;
    
    @Column(nullable = false)
    private Boolean disponible;
    
    private Integer solicitudesAsignadas;
    
    private Double calificacionPromedio;
    
    private LocalDateTime ultimaActualizacion;
    
    @PrePersist
    @PreUpdate
    protected void actualizarFecha() {
        ultimaActualizacion = LocalDateTime.now();
    }
}
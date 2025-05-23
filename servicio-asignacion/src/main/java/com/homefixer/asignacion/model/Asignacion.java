package com.homefixer.asignacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "asignaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long solicitudId;
    
    @Column(nullable = false)
    private Long tecnicoId;
    
    @Column(nullable = false)
    private LocalDateTime fechaAsignacion;
    
    private Double distanciaKm;
    
    private Integer tiempoEstimadoMinutos;
    
    @Column(nullable = false)
    private String estado;
    
    @PrePersist
    protected void onCreate() {
        fechaAsignacion = LocalDateTime.now();
        estado = "ASIGNADA";
    }
}
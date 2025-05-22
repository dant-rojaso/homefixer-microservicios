package com.homefixer.notificaciones.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long clienteId;
    
    @Column(nullable = false)
    private Long solicitudId;
    
    private Long tecnicoId;
    
    @Column(nullable = false)
    private String tipo; // ESTADO, ASIGNACION, PAGO, VALORACION
    
    @Column(nullable = false)
    private String mensaje;
    
    @Column(nullable = false)
    private String estadoAnterior;
    
    @Column(nullable = false)
    private String estadoNuevo;
    
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
    
    private Boolean leida;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        leida = false;
    }
}
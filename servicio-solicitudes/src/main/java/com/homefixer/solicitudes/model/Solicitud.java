package com.homefixer.solicitudes.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity // Marca esta clase como una entidad JPA que se mapeará a una tabla
@Table(name = "solicitudes") // Especifica el nombre de la tabla en la BD
@Data // Lombok: genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Lombok: genera constructor sin argumentos
@AllArgsConstructor // Lombok: genera constructor con todos los argumentos
public class Solicitud {
    
    @Id // Marca este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento en la BD
    private Long id;
    
    @Column(nullable = false) // Campo obligatorio en la BD
    private Long clienteId;
    
    @Column(nullable = false)
    private String tipoServicio; // ELECTRICIDAD, PLOMERIA, CARPINTERIA, etc.
    
    @Column(length = 1000) // Limita la longitud del campo a 1000 caracteres
    private String descripcionProblema;
    
    @Column(nullable = false)
    private String direccion;
    
    @Column(nullable = false)
    private String estado; // PENDIENTE, ASIGNADA, EN_PROCESO, COMPLETADA, CANCELADA
    
    private Long tecnicoId; // Puede ser null hasta que se asigne un técnico
    
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
    
    private LocalDateTime fechaAsignacion; // Se llena cuando se asigna un técnico
    
    private LocalDateTime fechaCompletado; // Se llena cuando se completa el servicio
    
    private Double presupuesto; // Costo estimado del servicio
    
    @PrePersist // Se ejecuta antes de guardar por primera vez
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        estado = "PENDIENTE"; // Estado inicial por defecto
    }
}
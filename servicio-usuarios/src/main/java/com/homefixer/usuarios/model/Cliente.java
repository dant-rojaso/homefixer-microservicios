package com.homefixer.usuarios.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Secuencia independiente: 1, 2, 3, 4...
    
    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;
    
    @Email(message = "Email debe ser válido")
    @NotBlank(message = "El email es obligatorio")
    @Column(nullable = false, unique = true)
    private String email;
    
    @NotBlank(message = "El teléfono es obligatorio")
    @Column(nullable = false)
    private String telefono;
    
    private String direccion;
    private String preferencias;
    
    @Column(name = "historial_servicios")
    private Integer historialServicios;
    
    @Column(name = "calificacion_promedio")
    private Double calificacionPromedio;
    
    @Column(nullable = false)
    private LocalDateTime fechaRegistro;
    
    @Column(nullable = false)
    private Boolean activo;
    
    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
        if (activo == null) activo = true;
        if (historialServicios == null) historialServicios = 0;
        if (calificacionPromedio == null) calificacionPromedio = 5.0;
    }
    
    public String getTipoUsuario() {
        return "CLIENTE";
    }
}
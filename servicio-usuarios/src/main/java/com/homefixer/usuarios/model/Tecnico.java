package com.homefixer.usuarios.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tecnicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tecnico {
    
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
    
    @NotBlank(message = "Las especialidades son obligatorias")
    @Column(nullable = false)
    private String especialidades; // "ELECTRICIDAD,PLOMERIA,CARPINTERIA"
    
    @Column(nullable = false)
    private Double latitud;
    
    @Column(nullable = false)
    private Double longitud;
    
    @Column(nullable = false)
    private Boolean disponible;
    
    @Column(name = "servicios_completados")
    private Integer serviciosCompletados;
    
    @Column(name = "calificacion_promedio")
    private Double calificacionPromedio;
    
    @Column(name = "precio_hora")
    private Double precioHora;
    
    @Column(nullable = false)
    private LocalDateTime fechaRegistro;
    
    @Column(nullable = false)
    private Boolean activo;
    
    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
        if (activo == null) activo = true;
        if (disponible == null) disponible = true;
        if (serviciosCompletados == null) serviciosCompletados = 0;
        if (calificacionPromedio == null) calificacionPromedio = 5.0;
    }
    
    public String getTipoUsuario() {
        return "TECNICO";
    }
}
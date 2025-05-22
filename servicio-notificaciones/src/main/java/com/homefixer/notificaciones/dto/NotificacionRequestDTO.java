package com.homefixer.notificaciones.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class NotificacionRequestDTO {
    
    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;
    
    @NotNull(message = "El ID de la solicitud es obligatorio")
    private Long solicitudId;
    
    private Long tecnicoId;
    
    @NotNull(message = "El tipo de notificación es obligatorio")
    private String tipo;
    
    @NotNull(message = "El mensaje es obligatorio")
    private String mensaje;
    
    @NotNull(message = "El estado anterior es obligatorio")
    private String estadoAnterior;
    
    @NotNull(message = "El estado nuevo es obligatorio")
    private String estadoNuevo;
}
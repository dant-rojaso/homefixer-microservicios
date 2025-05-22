package com.homefixer.notificaciones.controller;

import com.homefixer.notificaciones.model.Notificacion;
import com.homefixer.notificaciones.service.NotificacionService;
import com.homefixer.notificaciones.dto.NotificacionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotificacionController {
    
    private final NotificacionService notificacionService;
    
    @PostMapping("/enviar")
    public ResponseEntity<Notificacion> crearNotificacion(@Valid @RequestBody NotificacionRequestDTO requestDTO) {
        Notificacion nuevaNotificacion = notificacionService.crearNotificacion(requestDTO);
        return new ResponseEntity<>(nuevaNotificacion, HttpStatus.CREATED);
    }
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesPorCliente(@PathVariable Long clienteId) {
        List<Notificacion> notificaciones = notificacionService.obtenerNotificacionesPorCliente(clienteId);
        return ResponseEntity.ok(notificaciones);
    }
    
    @GetMapping("/cliente/{clienteId}/no-leidas")
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesNoLeidasPorCliente(@PathVariable Long clienteId) {
        List<Notificacion> notificaciones = notificacionService.obtenerNotificacionesNoLeidasPorCliente(clienteId);
        return ResponseEntity.ok(notificaciones);
    }
    
    @GetMapping("/cliente/{clienteId}/ultimas")
    public ResponseEntity<List<Notificacion>> obtenerUltimasNotificacionesPorCliente(@PathVariable Long clienteId) {
        List<Notificacion> notificaciones = notificacionService.obtenerUltimasNotificacionesPorCliente(clienteId);
        return ResponseEntity.ok(notificaciones);
    }
    
    @GetMapping("/solicitud/{solicitudId}")
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesPorSolicitud(@PathVariable Long solicitudId) {
        List<Notificacion> notificaciones = notificacionService.obtenerNotificacionesPorSolicitud(solicitudId);
        return ResponseEntity.ok(notificaciones);
    }
    
    @GetMapping("/tecnico/{tecnicoId}")
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesPorTecnico(@PathVariable Long tecnicoId) {
        List<Notificacion> notificaciones = notificacionService.obtenerNotificacionesPorTecnico(tecnicoId);
        return ResponseEntity.ok(notificaciones);
    }
    
    @PutMapping("/{id}/leer")
    public ResponseEntity<Notificacion> marcarComoLeida(@PathVariable Long id) {
        Notificacion notificacion = notificacionService.marcarComoLeida(id);
        return ResponseEntity.ok(notificacion);
    }
    
    @PutMapping("/cliente/{clienteId}/leer-todas")
    public ResponseEntity<Void> marcarTodasComoLeidas(@PathVariable Long clienteId) {
        notificacionService.marcarTodasComoLeidas(clienteId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerNotificacionPorId(@PathVariable Long id) {
        Notificacion notificacion = notificacionService.obtenerNotificacionPorId(id);
        return ResponseEntity.ok(notificacion);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable Long id) {
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }
}
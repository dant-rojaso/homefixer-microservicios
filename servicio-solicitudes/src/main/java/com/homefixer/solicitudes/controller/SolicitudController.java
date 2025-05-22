package com.homefixer.solicitudes.controller;

import com.homefixer.solicitudes.model.Solicitud;
import com.homefixer.solicitudes.service.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController // Combina @Controller y @ResponseBody - todas las respuestas son JSON
@RequestMapping("/api/solicitudes") // Prefijo para todos los endpoints de este controlador
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite CORS desde cualquier origen
public class SolicitudController {
    
    private final SolicitudService solicitudService;
    
    @PostMapping // Maneja peticiones POST a /api/solicitudes
    public ResponseEntity<Solicitud> crearSolicitud(@Valid @RequestBody Solicitud solicitud) {
        // @Valid valida la solicitud según las anotaciones en el modelo
        // @RequestBody convierte el JSON del body a objeto Solicitud
        Solicitud nuevaSolicitud = solicitudService.crearSolicitud(solicitud);
        
        // Devuelve 201 Created con la solicitud creada
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}") // Maneja GET /api/solicitudes/{id}
    public ResponseEntity<Solicitud> obtenerSolicitudPorId(@PathVariable Long id) {
        // @PathVariable extrae el valor de {id} de la URL
        Solicitud solicitud = solicitudService.obtenerSolicitudPorId(id);
        return ResponseEntity.ok(solicitud); // Devuelve 200 OK
    }
    
    @GetMapping("/cliente/{clienteId}") // GET /api/solicitudes/cliente/{clienteId}
    public ResponseEntity<List<Solicitud>> obtenerSolicitudesPorCliente(@PathVariable Long clienteId) {
        List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesPorCliente(clienteId);
        return ResponseEntity.ok(solicitudes);
    }
    
    @GetMapping("/tecnico/{tecnicoId}")
    public ResponseEntity<List<Solicitud>> obtenerSolicitudesPorTecnico(@PathVariable Long tecnicoId) {
        List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesPorTecnico(tecnicoId);
        return ResponseEntity.ok(solicitudes);
    }
    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Solicitud>> obtenerSolicitudesPorEstado(@PathVariable String estado) {
        List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesPorEstado(estado);
        return ResponseEntity.ok(solicitudes);
    }
    
    @PutMapping("/{id}/estado") // PUT /api/solicitudes/{id}/estado?nuevoEstado=ASIGNADA
    public ResponseEntity<Solicitud> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) { // @RequestParam extrae parámetros de query
        Solicitud solicitudActualizada = solicitudService.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(solicitudActualizada);
    }
    
    @DeleteMapping("/{id}") // DELETE /api/solicitudes/{id}
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Long id) {
        solicitudService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }
}
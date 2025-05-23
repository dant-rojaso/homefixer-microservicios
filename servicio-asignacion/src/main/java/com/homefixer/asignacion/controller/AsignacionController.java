package com.homefixer.asignacion.controller;

import com.homefixer.asignacion.model.Asignacion;
import com.homefixer.asignacion.model.TecnicoDisponible;
import com.homefixer.asignacion.service.AsignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/asignacion")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AsignacionController {
    
    private final AsignacionService asignacionService;
    
    @PostMapping("/tecnicos")
    public ResponseEntity<TecnicoDisponible> registrarTecnico(@RequestBody TecnicoDisponible tecnico) {
        TecnicoDisponible nuevoTecnico = asignacionService.registrarTecnico(tecnico);
        return new ResponseEntity<>(nuevoTecnico, HttpStatus.CREATED);
    }
    
    @GetMapping("/tecnicos/disponibles")
    public ResponseEntity<List<TecnicoDisponible>> obtenerTecnicosDisponibles() {
        List<TecnicoDisponible> tecnicos = asignacionService.obtenerTecnicosDisponibles();
        return ResponseEntity.ok(tecnicos);
    }
    
    @PostMapping("/asignar")
    public ResponseEntity<Asignacion> crearAsignacionManual(
            @RequestParam Long solicitudId,
            @RequestParam Long tecnicoId) {
        Asignacion nuevaAsignacion = asignacionService.crearAsignacionManual(solicitudId, tecnicoId);
        return new ResponseEntity<>(nuevaAsignacion, HttpStatus.CREATED);
    }
    
    @PutMapping("/asignaciones/{id}/estado")
    public ResponseEntity<Asignacion> actualizarEstadoAsignacion(
            @PathVariable Long id,
            @RequestParam String nuevoEstado) {
        Asignacion asignacionActualizada = asignacionService.actualizarEstadoAsignacion(id, nuevoEstado);
        return ResponseEntity.ok(asignacionActualizada);
    }
    
    @GetMapping("/asignaciones/tecnico/{tecnicoId}")
    public ResponseEntity<List<Asignacion>> obtenerAsignacionesPorTecnico(@PathVariable Long tecnicoId) {
        List<Asignacion> asignaciones = asignacionService.obtenerAsignacionesPorTecnico(tecnicoId);
        return ResponseEntity.ok(asignaciones);
    }
    
    @GetMapping("/asignaciones/solicitud/{solicitudId}")
    public ResponseEntity<Asignacion> obtenerAsignacionPorSolicitud(@PathVariable Long solicitudId) {
        Asignacion asignacion = asignacionService.obtenerAsignacionPorSolicitud(solicitudId);
        return ResponseEntity.ok(asignacion);
    }
}
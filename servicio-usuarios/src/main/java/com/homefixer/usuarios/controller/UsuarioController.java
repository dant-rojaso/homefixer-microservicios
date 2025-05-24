package com.homefixer.usuarios.controller;

import com.homefixer.usuarios.model.Cliente;
import com.homefixer.usuarios.model.Tecnico;
import com.homefixer.usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    // ===== ENDPOINTS DE CLIENTES =====
    
    /**
     * Registra un nuevo cliente.
     * POST /api/usuarios/clientes
     */
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> registrarCliente(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = usuarioService.registrarCliente(cliente);
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Obtiene un cliente específico por ID.
     * GET /api/usuarios/clientes/{id}
     */
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        try {
            Cliente cliente = usuarioService.obtenerClientePorId(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Obtiene todos los clientes activos.
     * GET /api/usuarios/clientes
     */
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> obtenerClientesActivos() {
        List<Cliente> clientes = usuarioService.obtenerClientesActivos();
        return ResponseEntity.ok(clientes);
    }
    
    /**
     * Obtiene todos los clientes (activos e inactivos).
     * GET /api/usuarios/clientes/todos
     */
    @GetMapping("/clientes/todos")
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = usuarioService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }
    
    /**
     * Actualiza los datos de un cliente.
     * PUT /api/usuarios/clientes/{id}
     */
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteActualizado = usuarioService.actualizarCliente(id, cliente);
            return ResponseEntity.ok(clienteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Incrementa el historial de servicios de un cliente.
     * POST /api/usuarios/clientes/{id}/incrementar-servicios
     */
    @PostMapping("/clientes/{id}/incrementar-servicios")
    public ResponseEntity<Cliente> incrementarHistorialServicios(@PathVariable Long id) {
        try {
            Cliente cliente = usuarioService.incrementarHistorialServicios(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Desactiva un cliente.
     * DELETE /api/usuarios/clientes/{id}
     */
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> desactivarCliente(@PathVariable Long id) {
        try {
            usuarioService.desactivarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // ===== ENDPOINTS DE TÉCNICOS =====
    
    /**
     * Registra un nuevo técnico.
     * POST /api/usuarios/tecnicos
     */
    @PostMapping("/tecnicos")
    public ResponseEntity<Tecnico> registrarTecnico(@Valid @RequestBody Tecnico tecnico) {
        try {
            Tecnico nuevoTecnico = usuarioService.registrarTecnico(tecnico);
            return new ResponseEntity<>(nuevoTecnico, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Obtiene un técnico específico por ID.
     * GET /api/usuarios/tecnicos/{id}
     */
    @GetMapping("/tecnicos/{id}")
    public ResponseEntity<Tecnico> obtenerTecnicoPorId(@PathVariable Long id) {
        try {
            Tecnico tecnico = usuarioService.obtenerTecnicoPorId(id);
            return ResponseEntity.ok(tecnico);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Obtiene todos los técnicos activos.
     * GET /api/usuarios/tecnicos
     */
    @GetMapping("/tecnicos")
    public ResponseEntity<List<Tecnico>> obtenerTecnicosActivos() {
        List<Tecnico> tecnicos = usuarioService.obtenerTecnicosActivos();
        return ResponseEntity.ok(tecnicos);
    }
    
    /**
     * Obtiene técnicos disponibles para asignación.
     * GET /api/usuarios/tecnicos/disponibles
     */
    @GetMapping("/tecnicos/disponibles")
    public ResponseEntity<List<Tecnico>> obtenerTecnicosDisponibles() {
        List<Tecnico> tecnicos = usuarioService.obtenerTecnicosDisponibles();
        return ResponseEntity.ok(tecnicos);
    }
    
    /**
     * Obtiene técnicos por especialidad.
     * GET /api/usuarios/tecnicos/especialidad/{especialidad}
     */
    @GetMapping("/tecnicos/especialidad/{especialidad}")
    public ResponseEntity<List<Tecnico>> obtenerTecnicosPorEspecialidad(@PathVariable String especialidad) {
        List<Tecnico> tecnicos = usuarioService.obtenerTecnicosPorEspecialidad(especialidad);
        return ResponseEntity.ok(tecnicos);
    }
    
    /**
     * Obtiene técnicos mejor valorados.
     * GET /api/usuarios/tecnicos/mejor-valorados
     */
    @GetMapping("/tecnicos/mejor-valorados")
    public ResponseEntity<List<Tecnico>> obtenerTecnicosMejorValorados() {
        List<Tecnico> tecnicos = usuarioService.obtenerTecnicosMejorValorados();
        return ResponseEntity.ok(tecnicos);
    }
    
    /**
     * Actualiza los datos de un técnico.
     * PUT /api/usuarios/tecnicos/{id}
     */
    @PutMapping("/tecnicos/{id}")
    public ResponseEntity<Tecnico> actualizarTecnico(@PathVariable Long id, @RequestBody Tecnico tecnico) {
        try {
            Tecnico tecnicoActualizado = usuarioService.actualizarTecnico(id, tecnico);
            return ResponseEntity.ok(tecnicoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Actualiza la disponibilidad de un técnico.
     * PUT /api/usuarios/tecnicos/{id}/disponibilidad
     */
    @PutMapping("/tecnicos/{id}/disponibilidad")
    public ResponseEntity<Tecnico> actualizarDisponibilidadTecnico(
            @PathVariable Long id, 
            @RequestParam Boolean disponible) {
        try {
            Tecnico tecnico = usuarioService.actualizarDisponibilidadTecnico(id, disponible);
            return ResponseEntity.ok(tecnico);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Incrementa el contador de servicios completados de un técnico.
     * POST /api/usuarios/tecnicos/{id}/incrementar-servicios
     */
    @PostMapping("/tecnicos/{id}/incrementar-servicios")
    public ResponseEntity<Tecnico> incrementarServiciosCompletados(@PathVariable Long id) {
        try {
            Tecnico tecnico = usuarioService.incrementarServiciosCompletados(id);
            return ResponseEntity.ok(tecnico);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Actualiza la calificación promedio de un técnico.
     * PUT /api/usuarios/tecnicos/{id}/calificacion
     */
    @PutMapping("/tecnicos/{id}/calificacion")
    public ResponseEntity<Tecnico> actualizarCalificacionTecnico(
            @PathVariable Long id, 
            @RequestParam Double calificacion) {
        try {
            Tecnico tecnico = usuarioService.actualizarCalificacionTecnico(id, calificacion);
            return ResponseEntity.ok(tecnico);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Desactiva un técnico.
     * DELETE /api/usuarios/tecnicos/{id}
     */
    @DeleteMapping("/tecnicos/{id}")
    public ResponseEntity<Void> desactivarTecnico(@PathVariable Long id) {
        try {
            usuarioService.desactivarTecnico(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // ===== ENDPOINTS PARA COMUNICACIÓN ENTRE MICROSERVICIOS =====
    
    /**
     * Verifica si existe un cliente por ID.
     * GET /api/usuarios/clientes/{id}/existe
     * Usado por microservicio de Solicitudes para validar clientes.
     */
    @GetMapping("/clientes/{id}/existe")
    public ResponseEntity<Map<String, Boolean>> verificarExistenciaCliente(@PathVariable Long id) {
        boolean existe = usuarioService.existeCliente(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("existe", existe);
        return ResponseEntity.ok(respuesta);
    }
    
    /**
     * Verifica si un cliente está activo.
     * GET /api/usuarios/clientes/{id}/activo
     * Usado por microservicio de Solicitudes.
     */
    @GetMapping("/clientes/{id}/activo")
    public ResponseEntity<Map<String, Boolean>> verificarClienteActivo(@PathVariable Long id) {
        boolean activo = usuarioService.clienteEstaActivo(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("activo", activo);
        return ResponseEntity.ok(respuesta);
    }
    
    /**
     * Verifica si existe un técnico por ID.
     * GET /api/usuarios/tecnicos/{id}/existe
     * Usado por microservicio de Asignación para validar técnicos.
     */
    @GetMapping("/tecnicos/{id}/existe")
    public ResponseEntity<Map<String, Boolean>> verificarExistenciaTecnico(@PathVariable Long id) {
        boolean existe = usuarioService.existeTecnico(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("existe", existe);
        return ResponseEntity.ok(respuesta);
    }
    
    /**
     * Verifica si un técnico está disponible.
     * GET /api/usuarios/tecnicos/{id}/disponible
     * Usado por microservicio de Asignación.
     */
    @GetMapping("/tecnicos/{id}/disponible")
    public ResponseEntity<Map<String, Boolean>> verificarDisponibilidadTecnico(@PathVariable Long id) {
        boolean disponible = usuarioService.tecnicoEstaDisponible(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("disponible", disponible);
        return ResponseEntity.ok(respuesta);
    }
    
    /**
     * Obtiene información básica de un cliente para otros microservicios.
     * GET /api/usuarios/clientes/{id}/info
     */
    @GetMapping("/clientes/{id}/info")
    public ResponseEntity<Map<String, Object>> obtenerInfoCliente(@PathVariable Long id) {
        try {
            Cliente cliente = usuarioService.obtenerClientePorId(id);
            Map<String, Object> info = new HashMap<>();
            info.put("id", cliente.getId());
            info.put("nombre", cliente.getNombre());
            info.put("email", cliente.getEmail());
            info.put("telefono", cliente.getTelefono());
            info.put("activo", cliente.getActivo());
            return ResponseEntity.ok(info);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Obtiene información básica de un técnico para otros microservicios.
     * GET /api/usuarios/tecnicos/{id}/info
     */
    @GetMapping("/tecnicos/{id}/info")
    public ResponseEntity<Map<String, Object>> obtenerInfoTecnico(@PathVariable Long id) {
        try {
            Tecnico tecnico = usuarioService.obtenerTecnicoPorId(id);
            Map<String, Object> info = new HashMap<>();
            info.put("id", tecnico.getId());
            info.put("nombre", tecnico.getNombre());
            info.put("email", tecnico.getEmail());
            info.put("telefono", tecnico.getTelefono());
            info.put("especialidades", tecnico.getEspecialidades());
            info.put("disponible", tecnico.getDisponible());
            info.put("activo", tecnico.getActivo());
            info.put("calificacionPromedio", tecnico.getCalificacionPromedio());
            return ResponseEntity.ok(info);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // ===== ENDPOINTS DE ESTADÍSTICAS =====
    
    /**
     * Obtiene estadísticas generales del sistema de usuarios.
     * GET /api/usuarios/estadisticas
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticas() {
        Map<String, Object> estadisticas = new HashMap<>();
        
        // Estadísticas de clientes
        List<Cliente> todosClientes = usuarioService.obtenerTodosLosClientes();
        List<Cliente> clientesActivos = usuarioService.obtenerClientesActivos();
        
        estadisticas.put("totalClientes", todosClientes.size());
        estadisticas.put("clientesActivos", clientesActivos.size());
        estadisticas.put("clientesInactivos", todosClientes.size() - clientesActivos.size());
        
        // Estadísticas de técnicos
        List<Tecnico> tecnicosActivos = usuarioService.obtenerTecnicosActivos();
        List<Tecnico> tecnicosDisponibles = usuarioService.obtenerTecnicosDisponibles();
        
        estadisticas.put("totalTecnicos", tecnicosActivos.size());
        estadisticas.put("tecnicosDisponibles", tecnicosDisponibles.size());
        estadisticas.put("tecnicosOcupados", tecnicosActivos.size() - tecnicosDisponibles.size());
        
        // Especialidades más comunes
        Map<String, Long> especialidades = new HashMap<>();
        for (Tecnico tecnico : tecnicosActivos) {
            if (tecnico.getEspecialidades() != null) {
                String[] specs = tecnico.getEspecialidades().split(",");
                for (String spec : specs) {
                    especialidades.merge(spec.trim(), 1L, Long::sum);
                }
            }
        }
        estadisticas.put("especialidades", especialidades);
        
        return ResponseEntity.ok(estadisticas);
    }
}
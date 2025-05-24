package com.homefixer.usuarios.service;

import com.homefixer.usuarios.model.Cliente;
import com.homefixer.usuarios.model.Tecnico;
import com.homefixer.usuarios.repository.ClienteRepository;
import com.homefixer.usuarios.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;
    
    // ===== OPERACIONES DE CLIENTES =====
    
    @Transactional
    public Cliente registrarCliente(Cliente cliente) {
        // Verificar que el email no exista ni en clientes ni en técnicos
        if (clienteRepository.existsByEmail(cliente.getEmail()) || 
            tecnicoRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con este email");
        }
        
        return clienteRepository.save(cliente);
    }
    
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
    
    public List<Cliente> obtenerClientesActivos() {
        return clienteRepository.findByActivo(true);
    }
    
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }
    
    @Transactional
    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Cliente clienteExistente = obtenerClientePorId(id);
        
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());
        clienteExistente.setDireccion(clienteActualizado.getDireccion());
        clienteExistente.setPreferencias(clienteActualizado.getPreferencias());
        
        return clienteRepository.save(clienteExistente);
    }
    
    @Transactional
    public Cliente incrementarHistorialServicios(Long clienteId) {
        Cliente cliente = obtenerClientePorId(clienteId);
        cliente.setHistorialServicios(cliente.getHistorialServicios() + 1);
        return clienteRepository.save(cliente);
    }
    
    @Transactional
    public void desactivarCliente(Long id) {
        Cliente cliente = obtenerClientePorId(id);
        cliente.setActivo(false);
        clienteRepository.save(cliente);
    }
    
    // ===== OPERACIONES DE TÉCNICOS =====
    
    @Transactional
    public Tecnico registrarTecnico(Tecnico tecnico) {
        // Verificar que el email no exista ni en técnicos ni en clientes
        if (tecnicoRepository.existsByEmail(tecnico.getEmail()) || 
            clienteRepository.existsByEmail(tecnico.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con este email");
        }
        
        return tecnicoRepository.save(tecnico);
    }
    
    public Tecnico obtenerTecnicoPorId(Long id) {
        return tecnicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));
    }
    
    public List<Tecnico> obtenerTecnicosActivos() {
        return tecnicoRepository.findByActivo(true);
    }
    
    public List<Tecnico> obtenerTecnicosDisponibles() {
        return tecnicoRepository.findByActivoAndDisponible(true, true);
    }
    
    public List<Tecnico> obtenerTecnicosPorEspecialidad(String especialidad) {
        return tecnicoRepository.findByEspecialidadesContaining(especialidad.toUpperCase());
    }
    
    public List<Tecnico> obtenerTecnicosMejorValorados() {
        return tecnicoRepository.findTecnicosMejorValorados();
    }
    
    @Transactional
    public Tecnico actualizarTecnico(Long id, Tecnico tecnicoActualizado) {
        Tecnico tecnicoExistente = obtenerTecnicoPorId(id);
        
        tecnicoExistente.setNombre(tecnicoActualizado.getNombre());
        tecnicoExistente.setTelefono(tecnicoActualizado.getTelefono());
        tecnicoExistente.setDireccion(tecnicoActualizado.getDireccion());
        tecnicoExistente.setEspecialidades(tecnicoActualizado.getEspecialidades());
        tecnicoExistente.setLatitud(tecnicoActualizado.getLatitud());
        tecnicoExistente.setLongitud(tecnicoActualizado.getLongitud());
        tecnicoExistente.setPrecioHora(tecnicoActualizado.getPrecioHora());
        
        return tecnicoRepository.save(tecnicoExistente);
    }
    
    @Transactional
    public Tecnico actualizarDisponibilidadTecnico(Long tecnicoId, Boolean disponible) {
        Tecnico tecnico = obtenerTecnicoPorId(tecnicoId);
        tecnico.setDisponible(disponible);
        return tecnicoRepository.save(tecnico);
    }
    
    @Transactional
    public Tecnico incrementarServiciosCompletados(Long tecnicoId) {
        Tecnico tecnico = obtenerTecnicoPorId(tecnicoId);
        tecnico.setServiciosCompletados(tecnico.getServiciosCompletados() + 1);
        return tecnicoRepository.save(tecnico);
    }
    
    @Transactional
    public Tecnico actualizarCalificacionTecnico(Long tecnicoId, Double nuevaCalificacion) {
        Tecnico tecnico = obtenerTecnicoPorId(tecnicoId);
        tecnico.setCalificacionPromedio(nuevaCalificacion);
        return tecnicoRepository.save(tecnico);
    }
    
    @Transactional
    public void desactivarTecnico(Long id) {
        Tecnico tecnico = obtenerTecnicoPorId(id);
        tecnico.setActivo(false);
        tecnico.setDisponible(false);
        tecnicoRepository.save(tecnico);
    }
    
    // ===== OPERACIONES DE VALIDACIÓN PARA OTROS MICROSERVICIOS =====
    
    public boolean existeCliente(Long id) {
        return clienteRepository.existsById(id);
    }
    
    public boolean existeTecnico(Long id) {
        return tecnicoRepository.existsById(id);
    }
    
    public boolean clienteEstaActivo(Long clienteId) {
        try {
            Cliente cliente = obtenerClientePorId(clienteId);
            return cliente.getActivo();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean tecnicoEstaDisponible(Long tecnicoId) {
        try {
            Tecnico tecnico = obtenerTecnicoPorId(tecnicoId);
            return tecnico.getActivo() && tecnico.getDisponible();
        } catch (Exception e) {
            return false;
        }
    }
}
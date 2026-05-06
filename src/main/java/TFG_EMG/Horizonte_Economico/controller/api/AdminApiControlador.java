package TFG_EMG.Horizonte_Economico.controller.api;

/**
 * DTO con datos agregados para el panel de administración
 */
import TFG_EMG.Horizonte_Economico.dto.AdminDashboardDTO;

/**
 * DTO para actualizar datos de usuario desde admin
 */
import TFG_EMG.Horizonte_Economico.dto.AdminUsuarioActualizarSolicitud;

/**
 * Entidad Usuario
 */
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;

/**
 * Servicio con la lógica de administración
 */
import TFG_EMG.Horizonte_Economico.service.AdminServicio;

/**
 * Validación de datos de entrada
 */
import jakarta.validation.Valid;

/**
 * Respuestas HTTP
 */
import org.springframework.http.ResponseEntity;

/**
 * Anotaciones REST
 */
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operaciones de administrador
 */
@RestController
@RequestMapping("/api/v1/admin")
public class AdminApiControlador {

    /**
     * Servicio que contiene la lógica de negocio de administración
     */
    private final AdminServicio adminServicio;

    /**
     * Inicializa las dependencias necesarias para AdminApiControlador.
     */
    public AdminApiControlador(AdminServicio adminServicio) {
        this.adminServicio = adminServicio;
    }

    /**
     * Obtiene datos agregados para el dashboard del administrador
     */
    @GetMapping("/dashboard")
    public AdminDashboardDTO dashboard() {
        return adminServicio.dashboard();
    }

    /**
     * Devuelve la lista completa de usuarios
     */
    @GetMapping("/usuarios")
    public List<Usuario> usuarios() {
        return adminServicio.listarUsuarios();
    }

    /**
     * Actualiza los datos de un usuario concreto
     */
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Long id,
                                           @Valid @RequestBody AdminUsuarioActualizarSolicitud solicitud) {
        adminServicio.actualizarUsuario(id, solicitud);
        return ResponseEntity.noContent().build(); // 204 sin contenido
    }

    /**
     * Activa un usuario
     */
    @PostMapping("/usuarios/{id}/activar")
    public ResponseEntity<Void> activar(@PathVariable Long id) {
        adminServicio.activar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Desactiva un usuario
     */
    @PostMapping("/usuarios/{id}/desactivar")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        adminServicio.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}

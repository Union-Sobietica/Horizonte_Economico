package TFG_EMG.Horizonte_Economico.controller.api;

/**
 * DTO para actualizar datos de la familia
 */
import TFG_EMG.Horizonte_Economico.dto.FamiliaActualizarSolicitud;

/**
 * DTO para crear miembros familiares
 */
import TFG_EMG.Horizonte_Economico.dto.MiembroFamiliarCrearSolicitud;

/**
 * Entidades de familia y miembros
 */
import TFG_EMG.Horizonte_Economico.model.entity.Familia;
import TFG_EMG.Horizonte_Economico.model.entity.MiembroFamiliar;

/**
 * Servicio con la lógica de negocio de familia
 */
import TFG_EMG.Horizonte_Economico.service.FamiliaServicio;

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
 * Controlador REST para gestionar la información familiar
 */
@RestController
@RequestMapping("/api/v1/familia")
public class FamiliaApiControlador {

    /**
     * Servicio que gestiona la lógica de familia
     */
    private final FamiliaServicio familiaServicio;

    /**
     * Inicializa las dependencias necesarias para FamiliaApiControlador.
     */
    public FamiliaApiControlador(FamiliaServicio familiaServicio) {
        this.familiaServicio = familiaServicio;
    }

    /**
     * Obtiene la familia del usuario o la crea si no existe
     */
    @GetMapping
    public Familia obtener() {
        return familiaServicio.obtenerOCrearSiNoExiste();
    }

    /**
     * Actualiza los datos de la familia (estado civil, etc.)
     */
    @PutMapping
    public ResponseEntity<Void> actualizar(@Valid @RequestBody FamiliaActualizarSolicitud solicitud) {
        familiaServicio.actualizarFamilia(solicitud);
        return ResponseEntity.noContent().build();
    }

    /**
     * Lista todos los miembros de la familia
     */
    @GetMapping("/miembros")
    public List<MiembroFamiliar> listarMiembros() {
        return familiaServicio.listarMiembros();
    }

    /**
     * Añade un nuevo miembro familiar
     */
    @PostMapping("/miembros")
    public ResponseEntity<Void> crearMiembro(@Valid @RequestBody MiembroFamiliarCrearSolicitud solicitud) {
        familiaServicio.crearMiembro(solicitud);
        return ResponseEntity.ok().build();
    }

    /**
     * Elimina un miembro familiar por ID
     */
    @DeleteMapping("/miembros/{id}")
    public ResponseEntity<Void> borrarMiembro(@PathVariable Long id) {
        familiaServicio.borrarMiembro(id);
        return ResponseEntity.noContent().build();
    }
}

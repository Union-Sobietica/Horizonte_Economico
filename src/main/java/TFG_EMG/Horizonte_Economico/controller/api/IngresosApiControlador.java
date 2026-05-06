package TFG_EMG.Horizonte_Economico.controller.api;

/**
 * DTO para crear o actualizar ingresos
 */
import TFG_EMG.Horizonte_Economico.dto.IngresoCrearSolicitud;

/**
 * Entidad Ingreso
 */
import TFG_EMG.Horizonte_Economico.model.entity.Ingreso;

/**
 * Servicio con la lógica de negocio de ingresos
 */
import TFG_EMG.Horizonte_Economico.service.IngresoServicio;

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
 * Controlador REST para gestionar ingresos del usuario
 */
@RestController
@RequestMapping("/api/v1/ingresos")
public class IngresosApiControlador {

    /**
     * Servicio que gestiona la lógica de ingresos
     */
    private final IngresoServicio ingresoServicio;

    /**
     * Inicializa las dependencias necesarias para IngresosApiControlador.
     */
    public IngresosApiControlador(IngresoServicio ingresoServicio) {
        this.ingresoServicio = ingresoServicio;
    }

    /**
     * Obtiene la lista de ingresos del usuario
     */
    @GetMapping
    public List<Ingreso> listar() {
        return ingresoServicio.listar();
    }

    /**
     * Crea un nuevo ingreso
     */
    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody IngresoCrearSolicitud solicitud) {
        ingresoServicio.crear(solicitud);
        return ResponseEntity.ok().build();
    }

    /**
     * Elimina un ingreso por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        ingresoServicio.borrar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Actualiza un ingreso existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Long id,
                                           @Valid @RequestBody IngresoCrearSolicitud solicitud) {
        ingresoServicio.actualizar(id, solicitud);
        return ResponseEntity.noContent().build();
    }
}

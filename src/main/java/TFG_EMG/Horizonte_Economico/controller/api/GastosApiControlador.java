package TFG_EMG.Horizonte_Economico.controller.api;

/**
 * DTO para crear o actualizar gastos
 */
import TFG_EMG.Horizonte_Economico.dto.GastoCrearSolicitud;

/**
 * Entidad Gasto
 */
import TFG_EMG.Horizonte_Economico.model.entity.Gasto;

/**
 * Servicio con la lógica de negocio de gastos
 */
import TFG_EMG.Horizonte_Economico.service.GastoServicio;

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
 * Controlador REST para gestionar gastos del usuario
 */
@RestController
@RequestMapping("/api/v1/gastos")
public class GastosApiControlador {

    /**
     * Servicio que gestiona la lógica de gastos
     */
    private final GastoServicio gastoServicio;

    /**
     * Inicializa las dependencias necesarias para GastosApiControlador.
     */
    public GastosApiControlador(GastoServicio gastoServicio) {
        this.gastoServicio = gastoServicio;
    }

    /**
     * Obtiene la lista de gastos del usuario
     */
    @GetMapping
    public List<Gasto> listar() {
        return gastoServicio.listar();
    }

    /**
     * Crea un nuevo gasto
     */
    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody GastoCrearSolicitud solicitud) {
        gastoServicio.crear(solicitud);
        return ResponseEntity.ok().build();
    }

    /**
     * Elimina un gasto por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        gastoServicio.borrar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Actualiza un gasto existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Long id,
                                           @Valid @RequestBody GastoCrearSolicitud solicitud) {
        gastoServicio.actualizar(id, solicitud);
        return ResponseEntity.noContent().build();
    }
}

package TFG_EMG.Horizonte_Economico.controller.api;

/**
 * DTO para crear una nueva categoría
 */
import TFG_EMG.Horizonte_Economico.dto.CategoriaCrearSolicitud;

/**
 * Entidad Categoria
 */
import TFG_EMG.Horizonte_Economico.model.entity.Categoria;

/**
 * Servicio con la lógica de negocio de categorías
 */
import TFG_EMG.Horizonte_Economico.service.CategoriaServicio;

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
 * Controlador REST para gestionar categorías financieras
 */
@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriasApiControlador {

    /**
     * Servicio que gestiona la lógica de categorías
     */
    private final CategoriaServicio categoriaServicio;

    /**
     * Inicializa las dependencias necesarias para CategoriasApiControlador.
     */
    public CategoriasApiControlador(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    /**
     * Obtiene la lista de categorías del usuario
     */
    @GetMapping
    public List<Categoria> listar() {
        return categoriaServicio.listar();
    }

    /**
     * Crea una nueva categoría (ingreso o gasto)
     */
    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody CategoriaCrearSolicitud solicitud) {
        categoriaServicio.crear(solicitud);
        return ResponseEntity.ok().build();
    }

    /**
     * Elimina una categoría por su ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        categoriaServicio.borrar(id);
        return ResponseEntity.noContent().build(); // 204 sin contenido
    }
}

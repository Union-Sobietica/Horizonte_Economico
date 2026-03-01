package TFG_EMG.Horizonte_Economico.controller.api;

import TFG_EMG.Horizonte_Economico.dto.CategoriaCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Categoria;
import TFG_EMG.Horizonte_Economico.service.CategoriaServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriasApiControlador {

    private final CategoriaServicio categoriaServicio;

    public CategoriasApiControlador(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaServicio.listar();
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody CategoriaCrearSolicitud solicitud) {
        categoriaServicio.crear(solicitud);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        categoriaServicio.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
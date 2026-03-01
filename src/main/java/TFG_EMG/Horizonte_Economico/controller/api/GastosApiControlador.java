package TFG_EMG.Horizonte_Economico.controller.api;

import TFG_EMG.Horizonte_Economico.dto.GastoCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Gasto;
import TFG_EMG.Horizonte_Economico.service.GastoServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gastos")
public class GastosApiControlador {

    private final GastoServicio gastoServicio;

    public GastosApiControlador(GastoServicio gastoServicio) {
        this.gastoServicio = gastoServicio;
    }

    @GetMapping
    public List<Gasto> listar() {
        return gastoServicio.listar();
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody GastoCrearSolicitud solicitud) {
        gastoServicio.crear(solicitud);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        gastoServicio.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
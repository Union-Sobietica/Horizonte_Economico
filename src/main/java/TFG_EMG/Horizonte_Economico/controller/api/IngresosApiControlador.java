package TFG_EMG.Horizonte_Economico.controller.api;

import TFG_EMG.Horizonte_Economico.dto.IngresoCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Ingreso;
import TFG_EMG.Horizonte_Economico.service.IngresoServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingresos")
public class IngresosApiControlador {

    private final IngresoServicio ingresoServicio;

    public IngresosApiControlador(IngresoServicio ingresoServicio) {
        this.ingresoServicio = ingresoServicio;
    }

    @GetMapping
    public List<Ingreso> listar() {
        return ingresoServicio.listar();
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody IngresoCrearSolicitud solicitud) {
        ingresoServicio.crear(solicitud);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        ingresoServicio.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
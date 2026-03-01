package TFG_EMG.Horizonte_Economico.controller.api;

import TFG_EMG.Horizonte_Economico.dto.FamiliaActualizarSolicitud;
import TFG_EMG.Horizonte_Economico.dto.MiembroFamiliarCrearSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Familia;
import TFG_EMG.Horizonte_Economico.model.entity.MiembroFamiliar;
import TFG_EMG.Horizonte_Economico.service.FamiliaServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/familia")
public class FamiliaApiControlador {

    private final FamiliaServicio familiaServicio;

    public FamiliaApiControlador(FamiliaServicio familiaServicio) {
        this.familiaServicio = familiaServicio;
    }

    @GetMapping
    public Familia obtener() {
        return familiaServicio.obtenerOCrearSiNoExiste();
    }

    @PutMapping
    public ResponseEntity<Void> actualizar(@Valid @RequestBody FamiliaActualizarSolicitud solicitud) {
        familiaServicio.actualizarFamilia(solicitud);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/miembros")
    public List<MiembroFamiliar> listarMiembros() {
        return familiaServicio.listarMiembros();
    }

    @PostMapping("/miembros")
    public ResponseEntity<Void> crearMiembro(@Valid @RequestBody MiembroFamiliarCrearSolicitud solicitud) {
        familiaServicio.crearMiembro(solicitud);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/miembros/{id}")
    public ResponseEntity<Void> borrarMiembro(@PathVariable Long id) {
        familiaServicio.borrarMiembro(id);
        return ResponseEntity.noContent().build();
    }
}
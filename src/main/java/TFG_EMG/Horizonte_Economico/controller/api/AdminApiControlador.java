package TFG_EMG.Horizonte_Economico.controller.api;

import TFG_EMG.Horizonte_Economico.dto.AdminDashboardDTO;
import TFG_EMG.Horizonte_Economico.dto.AdminUsuarioActualizarSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.service.AdminServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminApiControlador {

    private final AdminServicio adminServicio;

    public AdminApiControlador(AdminServicio adminServicio) {
        this.adminServicio = adminServicio;
    }

    @GetMapping("/dashboard")
    public AdminDashboardDTO dashboard() {
        return adminServicio.dashboard();
    }

    @GetMapping("/usuarios")
    public List<Usuario> usuarios() {
        return adminServicio.listarUsuarios();
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Long id,
                                           @Valid @RequestBody AdminUsuarioActualizarSolicitud solicitud) {
        adminServicio.actualizarUsuario(id, solicitud);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/usuarios/{id}/activar")
    public ResponseEntity<Void> activar(@PathVariable Long id) {
        adminServicio.activar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/usuarios/{id}/desactivar")
    public ResponseEntity<Void> desactivar(@PathVariable Long id) {
        adminServicio.desactivar(id);
        return ResponseEntity.noContent().build();
    }
}
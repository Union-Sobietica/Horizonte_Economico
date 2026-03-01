package TFG_EMG.Horizonte_Economico.controller.web;

import TFG_EMG.Horizonte_Economico.dto.AdminUsuarioActualizarSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.RolUsuario;
import TFG_EMG.Horizonte_Economico.service.AdminServicio;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    private final AdminServicio adminServicio;

    public AdminControlador(AdminServicio adminServicio) {
        this.adminServicio = adminServicio;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("dashboard", adminServicio.dashboard());
        return "admin/dashboard";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", adminServicio.listarUsuarios());
        model.addAttribute("roles", RolUsuario.values());
        model.addAttribute("adminUsuarioActualizarSolicitud", new AdminUsuarioActualizarSolicitud());
        return "admin/usuarios";
    }

    @PostMapping("/usuarios/{id}")
    public String actualizarUsuario(@PathVariable Long id,
                                    @Valid @ModelAttribute AdminUsuarioActualizarSolicitud adminUsuarioActualizarSolicitud,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuarios", adminServicio.listarUsuarios());
            model.addAttribute("roles", RolUsuario.values());
            return "admin/usuarios";
        }
        adminServicio.actualizarUsuario(id, adminUsuarioActualizarSolicitud);
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/usuarios/{id}/activar")
    public String activar(@PathVariable Long id) {
        adminServicio.activar(id);
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/usuarios/{id}/desactivar")
    public String desactivar(@PathVariable Long id) {
        adminServicio.desactivar(id);
        return "redirect:/admin/usuarios";
    }
}
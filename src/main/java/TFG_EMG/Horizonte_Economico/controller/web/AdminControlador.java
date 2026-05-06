package TFG_EMG.Horizonte_Economico.controller.web;

/**
 * DTO para actualizar datos de usuario desde el panel admin
 */
import TFG_EMG.Horizonte_Economico.dto.AdminUsuarioActualizarSolicitud;

/**
 * Enum con los roles disponibles (USER, ADMIN)
 */
import TFG_EMG.Horizonte_Economico.model.entity.RolUsuario;

/**
 * Servicios de administración y usuario
 */
import TFG_EMG.Horizonte_Economico.service.AdminServicio;
import TFG_EMG.Horizonte_Economico.service.UsuarioServicio;

/**
 * Validación de formularios
 */
import jakarta.validation.Valid;

/**
 * Controlador MVC (renderiza vistas)
 */
import org.springframework.stereotype.Controller;

/**
 * Permite pasar datos a la vista (Thymeleaf)
 */
import org.springframework.ui.Model;

/**
 * Manejo de errores de validación
 */
import org.springframework.validation.BindingResult;

/**
 * Anotaciones web
 */
import org.springframework.web.bind.annotation.*;

/**
 * Permite pasar mensajes tras redirecciones
 */
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador web para la administración (no API REST)
 */
@Controller
@RequestMapping("/admin")
public class AdminControlador {

    /**
     * Servicio con lógica de administración
     */
    private final AdminServicio adminServicio;

    /**
     * Servicio de usuario (para reset de contraseña)
     */
    private final UsuarioServicio usuarioServicio;

    /**
     * Inicializa las dependencias necesarias para AdminControlador.
     */
    public AdminControlador(AdminServicio adminServicio, UsuarioServicio usuarioServicio) {
        this.adminServicio = adminServicio;
        this.usuarioServicio = usuarioServicio;
    }

    /**
     * Muestra el dashboard del administrador
     */
    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("dashboard", adminServicio.dashboard());
        return "admin/dashboard"; // vista Thymeleaf
    }

    /**
     * Muestra la gestión de usuarios
     */
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", adminServicio.listarUsuarios());
        model.addAttribute("roles", RolUsuario.values()); // lista de roles disponibles
        model.addAttribute("adminUsuarioActualizarSolicitud", new AdminUsuarioActualizarSolicitud());
        return "admin/usuarios";
    }

    /**
     * Actualiza datos de un usuario desde formulario
     */
    @PostMapping("/usuarios/{id}")
    public String actualizarUsuario(@PathVariable Long id,
                                    @Valid @ModelAttribute AdminUsuarioActualizarSolicitud adminUsuarioActualizarSolicitud,
                                    BindingResult bindingResult,
                                    Model model) {

        /**
         * Si hay errores de validación, recarga la vista con datos
         */
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuarios", adminServicio.listarUsuarios());
            model.addAttribute("roles", RolUsuario.values());
            return "admin/usuarios";
        }

        adminServicio.actualizarUsuario(id, adminUsuarioActualizarSolicitud);
        return "redirect:/admin/usuarios";
    }

    /**
     * Activa un usuario
     */
    @PostMapping("/usuarios/{id}/activar")
    public String activar(@PathVariable Long id) {
        adminServicio.activar(id);
        return "redirect:/admin/usuarios";
    }

    /**
     * Desactiva un usuario
     */
    @PostMapping("/usuarios/{id}/desactivar")
    public String desactivar(@PathVariable Long id) {
        adminServicio.desactivar(id);
        return "redirect:/admin/usuarios";
    }

    /**
     * Genera una contraseña temporal para un usuario (acción crítica de admin)
     */
    @PostMapping("/usuarios/{id}/reset-password")
    public String resetearPassword(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        /**
         * Genera nueva contraseña temporal
         */
        String temporal = usuarioServicio.resetearPasswordPorAdmin(id);

        /**
         * Envía mensaje a la vista tras redirección
         */
        redirectAttributes.addFlashAttribute("mensajeAdmin", "Contraseña temporal generada: " + temporal);

        return "redirect:/admin/usuarios";
    }
}

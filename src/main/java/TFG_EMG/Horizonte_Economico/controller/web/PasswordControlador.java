package TFG_EMG.Horizonte_Economico.controller.web;

/**
 * DTO para el formulario de cambio de contraseña
 */
import TFG_EMG.Horizonte_Economico.dto.CambiarPasswordSolicitud;

/**
 * Entidad Usuario
 */
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;

/**
 * Servicio para obtener el usuario autenticado
 */
import TFG_EMG.Horizonte_Economico.service.UsuarioActualServicio;

/**
 * Servicio con la lógica de usuario (cambio de contraseña)
 */
import TFG_EMG.Horizonte_Economico.service.UsuarioServicio;

/**
 * Validación de datos
 */
import jakarta.validation.Valid;

/**
 * Controlador MVC
 */
import org.springframework.stereotype.Controller;

/**
 * Modelo para pasar datos a la vista
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
 * Controlador para cambio de contraseña del usuario
 */
@Controller
@RequestMapping("/usuario")
public class PasswordControlador {

    /**
     * Servicio de usuario (lógica de cambio de contraseña)
     */
    private final UsuarioServicio usuarioServicio;

    /**
     * Servicio para obtener el usuario autenticado
     */
    private final UsuarioActualServicio usuarioActualServicio;

    /**
     * Inicializa las dependencias necesarias para PasswordControlador.
     */
    public PasswordControlador(UsuarioServicio usuarioServicio, UsuarioActualServicio usuarioActualServicio) {
        this.usuarioServicio = usuarioServicio;
        this.usuarioActualServicio = usuarioActualServicio;
    }

    /**
     * Muestra el formulario de cambio de contraseña
     */
    @GetMapping("/cambiar-password")
    public String cambiarPasswordForm(Model model) {
        model.addAttribute("cambiarPasswordSolicitud", new CambiarPasswordSolicitud());
        return "cambiar-password";
    }

    /**
     * Procesa el cambio de contraseña
     */
    @PostMapping("/cambiar-password")
    public String cambiarPassword(@Valid @ModelAttribute CambiarPasswordSolicitud cambiarPasswordSolicitud,
                                  BindingResult bindingResult,
                                  Model model) {

        /**
         * Si hay errores de validación, vuelve al formulario
         */
        if (bindingResult.hasErrors()) {
            return "cambiar-password";
        }

        try {
            /**
             * Obtiene el usuario autenticado
             */
            Usuario usuario = usuarioActualServicio.obtenerUsuarioActual();

            /**
             * Ejecuta el cambio de contraseña
             */
            usuarioServicio.cambiarPasswordUsuarioActual(cambiarPasswordSolicitud, usuario);

            /**
             * Captura errores de negocio (contraseña incorrecta, etc.)
             */
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorGeneral", ex.getMessage());
            return "cambiar-password";
        }

        /**
         * Redirige indicando que la contraseña se ha cambiado correctamente
         */
        return "redirect:/?passwordCambiada";
    }
}

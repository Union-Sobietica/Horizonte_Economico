package TFG_EMG.Horizonte_Economico.controller.web;

/**
 * DTO con los datos del formulario de registro
 */
import TFG_EMG.Horizonte_Economico.dto.RegistroSolicitud;

/**
 * Excepción cuando el email ya está registrado
 */
import TFG_EMG.Horizonte_Economico.exception.EmailYaRegistradoException;

/**
 * Servicio de usuario (registro)
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
 * Controlador de autenticación (login y registro)
 */
@Controller
public class AuthControlador {

    /**
     * Servicio que gestiona el registro de usuarios
     */
    private final UsuarioServicio usuarioServicio;

    /**
     * Inicializa las dependencias necesarias para AuthControlador.
     */
    public AuthControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    /**
     * Muestra la página de login
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Muestra el formulario de registro
     */
    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("registroSolicitud", new RegistroSolicitud());
        return "registro";
    }

    /**
     * Procesa el formulario de registro
     */
    @PostMapping("/registro")
    public String registrar(
            @Valid @ModelAttribute("registroSolicitud") RegistroSolicitud solicitud,
            BindingResult bindingResult,
            Model model
    ) {
        /**
         * Si hay errores de validación, vuelve al formulario
         */
        if (bindingResult.hasErrors()) {
            return "registro";
        }

        try {
            /**
             * Intenta registrar el usuario
             */
            usuarioServicio.registrar(solicitud);

            /**
             * Captura errores típicos (email duplicado o datos inválidos)
             */
        } catch (EmailYaRegistradoException | IllegalArgumentException ex) {
            model.addAttribute("errorGeneral", ex.getMessage());
            return "registro";
        }

        /**
         * Registro correcto → redirige a login con mensaje
         */
        return "redirect:/login?registro=ok";
    }
}

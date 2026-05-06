package TFG_EMG.Horizonte_Economico.controller.web;

/**
 * Controlador MVC para manejar páginas de error
 */
import org.springframework.stereotype.Controller;

/**
 * Anotaciones web
 */
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para errores de acceso
 */
@Controller
public class ErrorControlador {

    /**
     * Muestra la página cuando el usuario no tiene permisos (403)
     */
    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "acceso-denegado"; // vista Thymeleaf de error
    }
}

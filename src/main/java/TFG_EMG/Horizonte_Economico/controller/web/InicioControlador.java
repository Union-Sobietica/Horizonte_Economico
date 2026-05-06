package TFG_EMG.Horizonte_Economico.controller.web;

/**
 * Controlador MVC principal
 */
import org.springframework.stereotype.Controller;

/**
 * Anotación para mapear peticiones GET
 */
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador de la página de inicio
 */
@Controller
public class InicioControlador {

    /**
     * Muestra la página principal de la aplicación
     */
    @GetMapping("/")
    public String inicio() {
        return "index"; // vista Thymeleaf principal
    }
}

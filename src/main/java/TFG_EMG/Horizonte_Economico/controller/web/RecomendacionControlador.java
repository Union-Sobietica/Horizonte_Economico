package TFG_EMG.Horizonte_Economico.controller.web;

/**
 * Controlador MVC (devuelve vistas)
 */
import org.springframework.stereotype.Controller;

/**
 * Anotaciones web
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para la vista de recomendaciones
 */
@Controller
@RequestMapping("/usuario")
public class RecomendacionControlador {

    /**
     * Muestra la página de recomendaciones (generadas por IA)
     */
    @GetMapping("/recomendaciones")
    public String recomendaciones() {
        return "recomendaciones"; // vista Thymeleaf
    }
}

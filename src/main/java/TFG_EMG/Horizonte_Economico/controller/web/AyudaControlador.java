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
 * Controlador para la vista de ayudas/subvenciones
 */
@Controller
@RequestMapping("/usuario")
public class AyudaControlador {

    /**
     * Muestra la página de ayudas (vista Thymeleaf)
     */
    @GetMapping("/ayudas")
    public String ayudas() {
        return "ayudas";
    }
}

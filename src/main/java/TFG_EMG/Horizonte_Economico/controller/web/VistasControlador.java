package TFG_EMG.Horizonte_Economico.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Agrupa vistas simples que no necesitan preparar datos en el modelo.
 */
@Controller
public class VistasControlador {

    /**
     * Muestra la pagina principal de la aplicacion.
     */
    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    /**
     * Muestra la pagina cuando el usuario no tiene permisos.
     */
    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "acceso-denegado";
    }

    /**
     * Muestra la vista de recomendaciones generadas por IA.
     */
    @GetMapping("/usuario/recomendaciones")
    public String recomendaciones() {
        return "recomendaciones";
    }

    /**
     * Muestra la vista de ayudas y subvenciones.
     */
    @GetMapping("/usuario/ayudas")
    public String ayudas() {
        return "ayudas";
    }
}

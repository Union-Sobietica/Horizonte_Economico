package TFG_EMG.Horizonte_Economico.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Centraliza el manejo de errores relacionado con controlador excepciones global.
 */
@ControllerAdvice
public class ControladorExcepcionesGlobal {

    /**
     * Transforma la excepcion recibida en una respuesta controlada.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public String manejarIllegalArgument(IllegalArgumentException ex, Model model) {
        model.addAttribute("titulo", "Operación no válida");
        model.addAttribute("mensaje", ex.getMessage());
        return "error-personalizado";
    }

    /**
     * Transforma la excepcion recibida en una respuesta controlada.
     */
    @ExceptionHandler(Exception.class)
    public String manejarExcepcionGeneral(Exception ex, Model model) {
        model.addAttribute("titulo", "Error interno");
        model.addAttribute("mensaje", "Se ha producido un error inesperado.");
        return "error-personalizado";
    }
}

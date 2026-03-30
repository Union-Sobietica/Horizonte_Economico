package TFG_EMG.Horizonte_Economico.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControladorExcepcionesGlobal {

    @ExceptionHandler(IllegalArgumentException.class)
    public String manejarIllegalArgument(IllegalArgumentException ex, Model model) {
        model.addAttribute("titulo", "Operación no válida");
        model.addAttribute("mensaje", ex.getMessage());
        return "error-personalizado";
    }

    @ExceptionHandler(Exception.class)
    public String manejarExcepcionGeneral(Exception ex, Model model) {
        model.addAttribute("titulo", "Error interno");
        model.addAttribute("mensaje", "Se ha producido un error inesperado.");
        return "error-personalizado";
    }
}
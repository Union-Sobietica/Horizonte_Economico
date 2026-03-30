package TFG_EMG.Horizonte_Economico.controller.web;

import TFG_EMG.Horizonte_Economico.dto.CambiarPasswordSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.service.UsuarioActualServicio;
import TFG_EMG.Horizonte_Economico.service.UsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class PasswordControlador {

    private final UsuarioServicio usuarioServicio;
    private final UsuarioActualServicio usuarioActualServicio;

    public PasswordControlador(UsuarioServicio usuarioServicio, UsuarioActualServicio usuarioActualServicio) {
        this.usuarioServicio = usuarioServicio;
        this.usuarioActualServicio = usuarioActualServicio;
    }

    @GetMapping("/cambiar-password")
    public String cambiarPasswordForm(Model model) {
        model.addAttribute("cambiarPasswordSolicitud", new CambiarPasswordSolicitud());
        return "cambiar-password";
    }

    @PostMapping("/cambiar-password")
    public String cambiarPassword(@Valid @ModelAttribute CambiarPasswordSolicitud cambiarPasswordSolicitud,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            return "cambiar-password";
        }

        try {
            Usuario usuario = usuarioActualServicio.obtenerUsuarioActual();
            usuarioServicio.cambiarPasswordUsuarioActual(cambiarPasswordSolicitud, usuario);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorGeneral", ex.getMessage());
            return "cambiar-password";
        }

        return "redirect:/?passwordCambiada";
    }
}
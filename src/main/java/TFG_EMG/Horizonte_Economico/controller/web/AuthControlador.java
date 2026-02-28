package TFG_EMG.Horizonte_Economico.controller.web;

import TFG_EMG.Horizonte_Economico.dto.RegistroSolicitud;
import TFG_EMG.Horizonte_Economico.exception.EmailYaRegistradoException;
import TFG_EMG.Horizonte_Economico.service.UsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthControlador {

    private final UsuarioServicio usuarioServicio;

    public AuthControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("registroSolicitud", new RegistroSolicitud());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(
            @Valid @ModelAttribute("registroSolicitud") RegistroSolicitud solicitud,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "registro";
        }

        try {
            usuarioServicio.registrar(solicitud);
        } catch (EmailYaRegistradoException | IllegalArgumentException ex) {
            model.addAttribute("errorGeneral", ex.getMessage());
            return "registro";
        }

        return "redirect:/login?registro=ok";
    }
}
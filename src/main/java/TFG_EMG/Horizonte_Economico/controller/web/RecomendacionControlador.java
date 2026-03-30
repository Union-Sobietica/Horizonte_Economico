package TFG_EMG.Horizonte_Economico.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class RecomendacionControlador {

    @GetMapping("/recomendaciones")
    public String recomendaciones() {
        return "recomendaciones";
    }
}
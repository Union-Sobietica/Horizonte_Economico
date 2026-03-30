package TFG_EMG.Horizonte_Economico.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class AyudaControlador {

    @GetMapping("/ayudas")
    public String ayudas() {
        return "ayudas";
    }
}
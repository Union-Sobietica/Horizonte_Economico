package TFG_EMG.Horizonte_Economico.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminControlador {

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
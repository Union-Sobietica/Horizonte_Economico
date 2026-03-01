package TFG_EMG.Horizonte_Economico.controller.web;

import TFG_EMG.Horizonte_Economico.service.MetricaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class ResumenFamiliarControlador {

    private final MetricaServicio metricaServicio;

    public ResumenFamiliarControlador(MetricaServicio metricaServicio) {
        this.metricaServicio = metricaServicio;
    }

    @GetMapping("/resumen-familia")
    public String resumenFamilia(@RequestParam(name = "mes", required = false) String mes, Model model) {
        var dto = metricaServicio.resumenFamiliar(mes);
        model.addAttribute("resumen", dto);
        model.addAttribute("mesSeleccionado", dto.getMes());
        return "resumen-familia";
    }
}
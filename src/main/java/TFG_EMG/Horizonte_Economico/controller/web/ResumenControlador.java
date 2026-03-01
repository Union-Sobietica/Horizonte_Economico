package TFG_EMG.Horizonte_Economico.controller.web;

import TFG_EMG.Horizonte_Economico.dto.ResumenMensualDTO;
import TFG_EMG.Horizonte_Economico.service.MetricaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@Controller
@RequestMapping("/usuario")
public class ResumenControlador {

    private final MetricaServicio metricaServicio;

    public ResumenControlador(MetricaServicio metricaServicio) {
        this.metricaServicio = metricaServicio;
    }

    @GetMapping("/resumen")
    public String resumen(@RequestParam(name = "mes", required = false) String mes, Model model) {
        ResumenMensualDTO dto = metricaServicio.resumenMensual(mes);

        String mesSeleccionado = dto.getMes();
        model.addAttribute("resumen", dto);
        model.addAttribute("mesSeleccionado", mesSeleccionado);
        model.addAttribute("mesActual", YearMonth.now().toString());

        return "resumen";
    }
}
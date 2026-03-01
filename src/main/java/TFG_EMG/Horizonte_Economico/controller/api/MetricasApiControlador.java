package TFG_EMG.Horizonte_Economico.controller.api;

import TFG_EMG.Horizonte_Economico.dto.ResumenMensualDTO;
import TFG_EMG.Horizonte_Economico.service.MetricaServicio;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/metricas")
public class MetricasApiControlador {

    private final MetricaServicio metricaServicio;

    public MetricasApiControlador(MetricaServicio metricaServicio) {
        this.metricaServicio = metricaServicio;
    }

    @GetMapping("/mensual")
    public ResumenMensualDTO resumenMensual(@RequestParam(name = "mes", required = false) String mes) {
        return metricaServicio.resumenMensual(mes);
    }
}
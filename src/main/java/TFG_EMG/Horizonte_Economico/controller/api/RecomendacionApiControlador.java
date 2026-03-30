package TFG_EMG.Horizonte_Economico.controller.api;

import TFG_EMG.Horizonte_Economico.dto.IaRespuestaDTO;
import TFG_EMG.Horizonte_Economico.service.RecomendacionServicio;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recomendaciones")
public class RecomendacionApiControlador {

    private final RecomendacionServicio recomendacionServicio;

    public RecomendacionApiControlador(RecomendacionServicio recomendacionServicio) {
        this.recomendacionServicio = recomendacionServicio;
    }

    @GetMapping
    public IaRespuestaDTO recomendar(@RequestParam(required = false) String mes) {
        return recomendacionServicio.generarRecomendacion(mes);
    }
}
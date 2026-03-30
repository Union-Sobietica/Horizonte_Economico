package TFG_EMG.Horizonte_Economico.controller.api;

import TFG_EMG.Horizonte_Economico.dto.IaRespuestaDTO;
import TFG_EMG.Horizonte_Economico.service.AyudaServicio;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ayudas")
public class AyudaApiControlador {

    private final AyudaServicio ayudaServicio;

    public AyudaApiControlador(AyudaServicio ayudaServicio) {
        this.ayudaServicio = ayudaServicio;
    }

    @GetMapping
    public IaRespuestaDTO analizar() {
        return ayudaServicio.analizarAyudas();
    }
}
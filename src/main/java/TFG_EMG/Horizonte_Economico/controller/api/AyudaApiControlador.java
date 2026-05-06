package TFG_EMG.Horizonte_Economico.controller.api;

/**
 * DTO que contiene la respuesta generada por la IA
 */
import TFG_EMG.Horizonte_Economico.dto.IaRespuestaDTO;

/**
 * Servicio encargado del análisis de ayudas
 */
import TFG_EMG.Horizonte_Economico.service.AyudaServicio;

/**
 * Anotaciones REST
 */
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para el análisis de ayudas/subvenciones
 */
@RestController
@RequestMapping("/api/v1/ayudas")
public class AyudaApiControlador {

    /**
     * Servicio con la lógica de negocio
     */
    private final AyudaServicio ayudaServicio;

    /**
     * Inicializa las dependencias necesarias para AyudaApiControlador.
     */
    public AyudaApiControlador(AyudaServicio ayudaServicio) {
        this.ayudaServicio = ayudaServicio;
    }

    /**
     * Endpoint que analiza el perfil del usuario y devuelve posibles ayudas
     */
    @GetMapping
    public IaRespuestaDTO analizar(@RequestParam(defaultValue = "general") String tipo,
                                   @RequestParam(required = false) String promptPersonalizado) {
        return ayudaServicio.analizarAyudas(tipo, promptPersonalizado);
    }
}

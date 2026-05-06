package TFG_EMG.Horizonte_Economico.controller.api;

/**
 * DTO que contiene la respuesta generada por la IA
 */
import TFG_EMG.Horizonte_Economico.dto.IaRespuestaDTO;

/**
 * Servicio encargado de generar recomendaciones
 */
import TFG_EMG.Horizonte_Economico.service.RecomendacionServicio;

/**
 * Anotaciones REST
 */
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para recomendaciones de ahorro
 */
@RestController
@RequestMapping("/api/v1/recomendaciones")
public class RecomendacionApiControlador {

    /**
     * Servicio con la lógica de generación de recomendaciones
     */
    private final RecomendacionServicio recomendacionServicio;

    /**
     * Inicializa las dependencias necesarias para RecomendacionApiControlador.
     */
    public RecomendacionApiControlador(RecomendacionServicio recomendacionServicio) {
        this.recomendacionServicio = recomendacionServicio;
    }

    /**
     * Endpoint que genera recomendaciones personalizadas
     */
    /**
     * Puede recibir un mes opcional para analizar un periodo concreto
     */
    @GetMapping
    public IaRespuestaDTO recomendar(@RequestParam(required = false) String mes,
                                     @RequestParam(defaultValue = "general") String tipo,
                                     @RequestParam(required = false) String promptPersonalizado) {
        return recomendacionServicio.generarRecomendacion(mes, tipo, promptPersonalizado);
    }
}

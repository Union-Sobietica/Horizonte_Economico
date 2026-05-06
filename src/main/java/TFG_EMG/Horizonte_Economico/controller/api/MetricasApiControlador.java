package TFG_EMG.Horizonte_Economico.controller.api;

/**
 * DTO con el resumen financiero mensual individual
 */
import TFG_EMG.Horizonte_Economico.dto.ResumenMensualDTO;

/**
 * DTO con el resumen financiero familiar
 */
import TFG_EMG.Horizonte_Economico.dto.ResumenFamiliarDTO;

/**
 * Servicio que realiza los cálculos financieros
 */
import TFG_EMG.Horizonte_Economico.service.MetricaServicio;

/**
 * Anotaciones REST
 */
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para obtener métricas financieras
 */
@RestController
@RequestMapping("/api/v1/metricas")
public class MetricasApiControlador {

    /**
     * Servicio que contiene la lógica de cálculo
     */
    private final MetricaServicio metricaServicio;

    /**
     * Inicializa las dependencias necesarias para MetricasApiControlador.
     */
    public MetricasApiControlador(MetricaServicio metricaServicio) {
        this.metricaServicio = metricaServicio;
    }

    /**
     * Devuelve el resumen mensual individual (ingresos, gastos, balance)
     */
    /**
     * Permite filtrar por mes (opcional)
     */
    @GetMapping("/mensual")
    public ResumenMensualDTO resumenMensual(@RequestParam(name = "mes", required = false) String mes) {
        return metricaServicio.resumenMensual(mes);
    }

    /**
     * Devuelve el resumen mensual familiar (incluye ingresos familiares)
     */
    /**
     * También admite filtro opcional por mes
     */
    @GetMapping("/mensual-familiar")
    public ResumenFamiliarDTO resumenFamiliar(@RequestParam(name = "mes", required = false) String mes) {
        return metricaServicio.resumenFamiliar(mes);
    }
}

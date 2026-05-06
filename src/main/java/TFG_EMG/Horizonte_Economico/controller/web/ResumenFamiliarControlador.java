package TFG_EMG.Horizonte_Economico.controller.web;

/**
 * Servicio que calcula métricas financieras
 */
import TFG_EMG.Horizonte_Economico.service.MetricaServicio;

/**
 * Controlador MVC
 */
import org.springframework.stereotype.Controller;

/**
 * Modelo para pasar datos a la vista
 */
import org.springframework.ui.Model;

/**
 * Anotaciones web
 */
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para el resumen financiero familiar
 */
@Controller
@RequestMapping("/usuario")
public class ResumenFamiliarControlador {

    /**
     * Servicio con la lógica de cálculo familiar
     */
    private final MetricaServicio metricaServicio;

    /**
     * Inicializa las dependencias necesarias para ResumenFamiliarControlador.
     */
    public ResumenFamiliarControlador(MetricaServicio metricaServicio) {
        this.metricaServicio = metricaServicio;
    }

    /**
     * Muestra el resumen financiero familiar (puede filtrarse por mes)
     */
    @GetMapping("/resumen-familia")
    public String resumenFamilia(@RequestParam(name = "mes", required = false) String mes, Model model) {

        /**
         * Obtiene los datos del resumen familiar (incluye ingresos de miembros)
         */
        var dto = metricaServicio.resumenFamiliar(mes);

        /**
         * Datos enviados a la vista
         */
        model.addAttribute("resumen", dto);
        model.addAttribute("mesSeleccionado", dto.getMes());

        return "resumen-familia"; // vista Thymeleaf
    }
}

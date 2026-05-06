package TFG_EMG.Horizonte_Economico.controller.web;

/**
 * DTO con el resumen financiero mensual
 */
import TFG_EMG.Horizonte_Economico.dto.ResumenMensualDTO;

/**
 * Servicio que realiza los cálculos financieros
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

import java.time.YearMonth;

/**
 * Controlador para la vista de resumen financiero
 */
@Controller
@RequestMapping("/usuario")
public class ResumenControlador {

    /**
     * Servicio con la lógica de métricas
     */
    private final MetricaServicio metricaServicio;

    /**
     * Inicializa las dependencias necesarias para ResumenControlador.
     */
    public ResumenControlador(MetricaServicio metricaServicio) {
        this.metricaServicio = metricaServicio;
    }

    /**
     * Muestra el resumen mensual (puede filtrarse por mes)
     */
    @GetMapping("/resumen")
    public String resumen(@RequestParam(name = "mes", required = false) String mes, Model model) {

        /**
         * Obtiene los datos del resumen (ingresos, gastos, balance)
         */
        ResumenMensualDTO dto = metricaServicio.resumenMensual(mes);

        /**
         * Mes seleccionado (puede venir del usuario o por defecto)
         */
        String mesSeleccionado = dto.getMes();

        /**
         * Datos enviados a la vista
         */
        model.addAttribute("resumen", dto);
        model.addAttribute("mesSeleccionado", mesSeleccionado);
        model.addAttribute("mesActual", YearMonth.now().toString());

        return "resumen"; // vista Thymeleaf
    }
}

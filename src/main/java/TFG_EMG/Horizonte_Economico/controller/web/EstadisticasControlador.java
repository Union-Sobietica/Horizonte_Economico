package TFG_EMG.Horizonte_Economico.controller.web;

import TFG_EMG.Horizonte_Economico.service.ExcelServicio;
import TFG_EMG.Horizonte_Economico.service.MetricaServicio;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Year;

/**
 * Controlador MVC que prepara las vistas y formularios de estadisticas.
 */
@Controller
public class EstadisticasControlador {

    private final MetricaServicio metricaServicio;

    private final ExcelServicio excelServicio;

    /**
     * Inicializa las dependencias necesarias para EstadisticasControlador.
     */
    public EstadisticasControlador(MetricaServicio metricaServicio,
                                   ExcelServicio excelServicio) {
        this.metricaServicio = metricaServicio;
        this.excelServicio = excelServicio;
    }

    /**
     * Calcula las estadisticas agregadas solicitadas.
     */
    @GetMapping("/usuario/estadisticas")
    public String estadisticas(@RequestParam(required = false) Integer anio, Model model) {

        int anioSeleccionado = (anio != null) ? anio : Year.now().getValue();

        var estadisticas = metricaServicio.estadisticasAnuales(anioSeleccionado);

        model.addAttribute("estadisticas", estadisticas);
        model.addAttribute("anioSeleccionado", anioSeleccionado);

        return "estadisticas";
    }

    /**
     * Ejecuta la operacion descargarExcel dentro del flujo de EstadisticasControlador.
     */
    @GetMapping("/usuario/estadisticas/excel")
    public ResponseEntity<byte[]> descargarExcel(@RequestParam Integer anio) {

        var estadisticas = metricaServicio.estadisticasAnuales(anio);

        byte[] excel = excelServicio.generarExcelEstadisticas(estadisticas);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=estadisticas-" + anio + ".xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excel);
    }

}

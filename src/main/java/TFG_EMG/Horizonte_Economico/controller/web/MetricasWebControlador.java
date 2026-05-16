package TFG_EMG.Horizonte_Economico.controller.web;

import TFG_EMG.Horizonte_Economico.dto.ResumenMensualDTO;
import TFG_EMG.Horizonte_Economico.service.ExcelServicio;
import TFG_EMG.Horizonte_Economico.service.MetricaServicio;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Year;
import java.time.YearMonth;

/**
 * Controlador MVC para resumenes y estadisticas financieras.
 */
@Controller
@RequestMapping("/usuario")
public class MetricasWebControlador {

    private final MetricaServicio metricaServicio;
    private final ExcelServicio excelServicio;

    /**
     * Inicializa los servicios usados por las vistas de metricas.
     */
    public MetricasWebControlador(MetricaServicio metricaServicio, ExcelServicio excelServicio) {
        this.metricaServicio = metricaServicio;
        this.excelServicio = excelServicio;
    }

    /**
     * Muestra el resumen mensual individual.
     */
    @GetMapping("/resumen")
    public String resumen(@RequestParam(name = "mes", required = false) String mes, Model model) {
        ResumenMensualDTO dto = metricaServicio.resumenMensual(mes);

        model.addAttribute("resumen", dto);
        model.addAttribute("mesSeleccionado", dto.getMes());
        model.addAttribute("mesActual", YearMonth.now().toString());

        return "resumen";
    }

    /**
     * Muestra el resumen mensual familiar.
     */
    @GetMapping("/resumen-familia")
    public String resumenFamilia(@RequestParam(name = "mes", required = false) String mes, Model model) {
        var dto = metricaServicio.resumenFamiliar(mes);

        model.addAttribute("resumen", dto);
        model.addAttribute("mesSeleccionado", dto.getMes());

        return "resumen-familia";
    }

    /**
     * Muestra las estadisticas anuales.
     */
    @GetMapping("/estadisticas")
    public String estadisticas(@RequestParam(required = false) Integer anio, Model model) {
        int anioSeleccionado = (anio != null) ? anio : Year.now().getValue();
        var estadisticas = metricaServicio.estadisticasAnuales(anioSeleccionado);

        model.addAttribute("estadisticas", estadisticas);
        model.addAttribute("anioSeleccionado", anioSeleccionado);

        return "estadisticas";
    }

    /**
     * Descarga las estadisticas anuales en formato Excel.
     */
    @GetMapping("/estadisticas/excel")
    public ResponseEntity<byte[]> descargarExcel(@RequestParam Integer anio) {
        var estadisticas = metricaServicio.estadisticasAnuales(anio);
        byte[] excel = excelServicio.generarExcelEstadisticas(estadisticas);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=estadisticas-" + anio + ".xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excel);
    }
}

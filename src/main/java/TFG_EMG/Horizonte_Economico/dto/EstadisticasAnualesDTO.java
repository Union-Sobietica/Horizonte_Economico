package TFG_EMG.Horizonte_Economico.dto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Transporta datos de estadisticas anuales entre formularios, controladores y servicios.
 */
public class EstadisticasAnualesDTO {

    private int anio;
    private List<String> meses;
    private List<BigDecimal> ingresos;
    private List<BigDecimal> gastos;
    private List<List<CategoriaResumenDTO>> resumenesMensuales;
    private BigDecimal totalIngresos;
    private BigDecimal totalGastos;

    /**
     * Inicializa las dependencias necesarias para EstadisticasAnualesDTO.
     */
    public EstadisticasAnualesDTO(int anio,
                                  List<String> meses,
                                  List<BigDecimal> ingresos,
                                  List<BigDecimal> gastos,
                                  BigDecimal totalIngresos,
                                  BigDecimal totalGastos) {
        this(anio, meses, ingresos, gastos, Collections.emptyList(), totalIngresos, totalGastos);
    }

    /**
     * Inicializa las dependencias necesarias para EstadisticasAnualesDTO.
     */
    public EstadisticasAnualesDTO(int anio,
                                  List<String> meses,
                                  List<BigDecimal> ingresos,
                                  List<BigDecimal> gastos,
                                  List<List<CategoriaResumenDTO>> resumenesMensuales,
                                  BigDecimal totalIngresos,
                                  BigDecimal totalGastos) {
        this.anio = anio;
        this.meses = meses;
        this.ingresos = ingresos;
        this.gastos = gastos;
        this.resumenesMensuales = resumenesMensuales;
        this.totalIngresos = totalIngresos;
        this.totalGastos = totalGastos;
    }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public int getAnio() { return anio; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public List<String> getMeses() { return meses; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public List<BigDecimal> getIngresos() { return ingresos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public List<BigDecimal> getGastos() { return gastos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public List<List<CategoriaResumenDTO>> getResumenesMensuales() { return resumenesMensuales; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getTotalIngresos() { return totalIngresos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getTotalGastos() { return totalGastos; }
}

package TFG_EMG.Horizonte_Economico.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Transporta datos de resumen mensual entre formularios, controladores y servicios.
 */
public class ResumenMensualDTO {
    private String mes;
    private BigDecimal totalIngresos;
    private BigDecimal totalGastos;
    private BigDecimal sobrante;
    private List<CategoriaDistribucionDTO> distribucionGastos;
    private List<CategoriaResumenDTO> resumenCategorias;

    /**
     * Inicializa las dependencias necesarias para ResumenMensualDTO.
     */
    public ResumenMensualDTO() {}

    /**
     * Inicializa las dependencias necesarias para ResumenMensualDTO.
     */
    public ResumenMensualDTO(String mes,
                             BigDecimal totalIngresos,
                             BigDecimal totalGastos,
                             BigDecimal sobrante,
                             List<CategoriaDistribucionDTO> distribucionGastos,
                             List<CategoriaResumenDTO> resumenCategorias) {
        this.mes = mes;
        this.totalIngresos = totalIngresos;
        this.totalGastos = totalGastos;
        this.sobrante = sobrante;
        this.distribucionGastos = distribucionGastos;
        this.resumenCategorias = resumenCategorias;
    }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getMes() { return mes; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getTotalIngresos() { return totalIngresos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getTotalGastos() { return totalGastos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getSobrante() { return sobrante; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public List<CategoriaDistribucionDTO> getDistribucionGastos() { return distribucionGastos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public List<CategoriaResumenDTO> getResumenCategorias() { return resumenCategorias; }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setMes(String mes) { this.mes = mes; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setTotalIngresos(BigDecimal totalIngresos) { this.totalIngresos = totalIngresos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setTotalGastos(BigDecimal totalGastos) { this.totalGastos = totalGastos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setSobrante(BigDecimal sobrante) { this.sobrante = sobrante; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setDistribucionGastos(List<CategoriaDistribucionDTO> distribucionGastos) { this.distribucionGastos = distribucionGastos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setResumenCategorias(List<CategoriaResumenDTO> resumenCategorias) { this.resumenCategorias = resumenCategorias; }
}

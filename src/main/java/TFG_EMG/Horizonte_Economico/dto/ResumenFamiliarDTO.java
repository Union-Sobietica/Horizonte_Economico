package TFG_EMG.Horizonte_Economico.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Transporta datos de resumen familiar entre formularios, controladores y servicios.
 */
public class ResumenFamiliarDTO {
    private String mes;
    private BigDecimal totalIngresosUsuario;
    private BigDecimal totalIngresosFamiliaresExtra;
    private BigDecimal totalIngresosFamilia;
    private BigDecimal totalGastos;
    private BigDecimal sobranteFamiliar;
    private List<CategoriaDistribucionDTO> distribucionGastos;
    private List<CategoriaResumenDTO> resumenCategorias;

    /**
     * Inicializa las dependencias necesarias para ResumenFamiliarDTO.
     */
    public ResumenFamiliarDTO() {}

    /**
     * Inicializa las dependencias necesarias para ResumenFamiliarDTO.
     */
    public ResumenFamiliarDTO(String mes,
                              BigDecimal totalIngresosUsuario,
                              BigDecimal totalIngresosFamiliaresExtra,
                              BigDecimal totalIngresosFamilia,
                              BigDecimal totalGastos,
                              BigDecimal sobranteFamiliar,
                              List<CategoriaDistribucionDTO> distribucionGastos,
                              List<CategoriaResumenDTO> resumenCategorias) {
        this.mes = mes;
        this.totalIngresosUsuario = totalIngresosUsuario;
        this.totalIngresosFamiliaresExtra = totalIngresosFamiliaresExtra;
        this.totalIngresosFamilia = totalIngresosFamilia;
        this.totalGastos = totalGastos;
        this.sobranteFamiliar = sobranteFamiliar;
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
    public BigDecimal getTotalIngresosUsuario() { return totalIngresosUsuario; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getTotalIngresosFamiliaresExtra() { return totalIngresosFamiliaresExtra; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getTotalIngresosFamilia() { return totalIngresosFamilia; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getTotalGastos() { return totalGastos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getSobranteFamiliar() { return sobranteFamiliar; }
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
    public void setTotalIngresosUsuario(BigDecimal totalIngresosUsuario) { this.totalIngresosUsuario = totalIngresosUsuario; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setTotalIngresosFamiliaresExtra(BigDecimal totalIngresosFamiliaresExtra) { this.totalIngresosFamiliaresExtra = totalIngresosFamiliaresExtra; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setTotalIngresosFamilia(BigDecimal totalIngresosFamilia) { this.totalIngresosFamilia = totalIngresosFamilia; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setTotalGastos(BigDecimal totalGastos) { this.totalGastos = totalGastos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setSobranteFamiliar(BigDecimal sobranteFamiliar) { this.sobranteFamiliar = sobranteFamiliar; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setDistribucionGastos(List<CategoriaDistribucionDTO> distribucionGastos) { this.distribucionGastos = distribucionGastos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setResumenCategorias(List<CategoriaResumenDTO> resumenCategorias) { this.resumenCategorias = resumenCategorias; }
}

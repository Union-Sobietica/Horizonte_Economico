package TFG_EMG.Horizonte_Economico.dto;

import java.math.BigDecimal;
import java.util.List;

public class ResumenMensualDTO {
    private String mes;
    private BigDecimal totalIngresos;
    private BigDecimal totalGastos;
    private BigDecimal sobrante;
    private List<CategoriaDistribucionDTO> distribucionGastos;
    private List<CategoriaResumenDTO> resumenCategorias;

    public ResumenMensualDTO() {}

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

    public String getMes() { return mes; }
    public BigDecimal getTotalIngresos() { return totalIngresos; }
    public BigDecimal getTotalGastos() { return totalGastos; }
    public BigDecimal getSobrante() { return sobrante; }
    public List<CategoriaDistribucionDTO> getDistribucionGastos() { return distribucionGastos; }
    public List<CategoriaResumenDTO> getResumenCategorias() { return resumenCategorias; }

    public void setMes(String mes) { this.mes = mes; }
    public void setTotalIngresos(BigDecimal totalIngresos) { this.totalIngresos = totalIngresos; }
    public void setTotalGastos(BigDecimal totalGastos) { this.totalGastos = totalGastos; }
    public void setSobrante(BigDecimal sobrante) { this.sobrante = sobrante; }
    public void setDistribucionGastos(List<CategoriaDistribucionDTO> distribucionGastos) { this.distribucionGastos = distribucionGastos; }
    public void setResumenCategorias(List<CategoriaResumenDTO> resumenCategorias) { this.resumenCategorias = resumenCategorias; }
}
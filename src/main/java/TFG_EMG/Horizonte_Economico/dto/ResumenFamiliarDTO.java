package TFG_EMG.Horizonte_Economico.dto;

import java.math.BigDecimal;
import java.util.List;

public class ResumenFamiliarDTO {
    private String mes;
    private BigDecimal totalIngresosUsuario;
    private BigDecimal totalIngresosFamiliaresExtra;
    private BigDecimal totalIngresosFamilia;
    private BigDecimal totalGastos;
    private BigDecimal sobranteFamiliar;
    private List<CategoriaDistribucionDTO> distribucionGastos;

    public ResumenFamiliarDTO() {}

    public ResumenFamiliarDTO(String mes,
                              BigDecimal totalIngresosUsuario,
                              BigDecimal totalIngresosFamiliaresExtra,
                              BigDecimal totalIngresosFamilia,
                              BigDecimal totalGastos,
                              BigDecimal sobranteFamiliar,
                              List<CategoriaDistribucionDTO> distribucionGastos) {
        this.mes = mes;
        this.totalIngresosUsuario = totalIngresosUsuario;
        this.totalIngresosFamiliaresExtra = totalIngresosFamiliaresExtra;
        this.totalIngresosFamilia = totalIngresosFamilia;
        this.totalGastos = totalGastos;
        this.sobranteFamiliar = sobranteFamiliar;
        this.distribucionGastos = distribucionGastos;
    }

    public String getMes() { return mes; }
    public BigDecimal getTotalIngresosUsuario() { return totalIngresosUsuario; }
    public BigDecimal getTotalIngresosFamiliaresExtra() { return totalIngresosFamiliaresExtra; }
    public BigDecimal getTotalIngresosFamilia() { return totalIngresosFamilia; }
    public BigDecimal getTotalGastos() { return totalGastos; }
    public BigDecimal getSobranteFamiliar() { return sobranteFamiliar; }
    public List<CategoriaDistribucionDTO> getDistribucionGastos() { return distribucionGastos; }

    public void setMes(String mes) { this.mes = mes; }
    public void setTotalIngresosUsuario(BigDecimal totalIngresosUsuario) { this.totalIngresosUsuario = totalIngresosUsuario; }
    public void setTotalIngresosFamiliaresExtra(BigDecimal totalIngresosFamiliaresExtra) { this.totalIngresosFamiliaresExtra = totalIngresosFamiliaresExtra; }
    public void setTotalIngresosFamilia(BigDecimal totalIngresosFamilia) { this.totalIngresosFamilia = totalIngresosFamilia; }
    public void setTotalGastos(BigDecimal totalGastos) { this.totalGastos = totalGastos; }
    public void setSobranteFamiliar(BigDecimal sobranteFamiliar) { this.sobranteFamiliar = sobranteFamiliar; }
    public void setDistribucionGastos(List<CategoriaDistribucionDTO> distribucionGastos) { this.distribucionGastos = distribucionGastos; }
}
package TFG_EMG.Horizonte_Economico.dto;

import java.math.BigDecimal;

public class CategoriaDistribucionDTO {
    private String categoria;
    private BigDecimal total;
    private BigDecimal porcentaje;

    public CategoriaDistribucionDTO() {}

    public CategoriaDistribucionDTO(String categoria, BigDecimal total, BigDecimal porcentaje) {
        this.categoria = categoria;
        this.total = total;
        this.porcentaje = porcentaje;
    }

    public String getCategoria() { return categoria; }
    public BigDecimal getTotal() { return total; }
    public BigDecimal getPorcentaje() { return porcentaje; }

    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }
}
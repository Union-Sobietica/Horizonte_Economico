package TFG_EMG.Horizonte_Economico.dto;

import java.math.BigDecimal;

public class CategoriaResumenDTO {

    private String categoria;
    private String tipo;
    private BigDecimal total;

    public CategoriaResumenDTO() {}

    public CategoriaResumenDTO(String categoria, String tipo, BigDecimal total) {
        this.categoria = categoria;
        this.tipo = tipo;
        this.total = total;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
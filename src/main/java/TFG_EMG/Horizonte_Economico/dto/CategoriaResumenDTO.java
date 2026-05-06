package TFG_EMG.Horizonte_Economico.dto;

/**
 * Para representar cantidades monetarias con precisión
 */
import java.math.BigDecimal;

/**
 * DTO para resumir totales por categoría
 */
public class CategoriaResumenDTO {

    /**
     * Nombre de la categoría
     */
    private String categoria;

    /**
     * Tipo de categoría (INGRESO o GASTO)
     */
    private String tipo;

    /**
     * Total acumulado en esa categoría
     */
    private BigDecimal total;

    /**
     * Constructor vacío
     */
    public CategoriaResumenDTO() {}

    /**
     * Constructor completo
     */
    public CategoriaResumenDTO(String categoria, String tipo, BigDecimal total) {
        this.categoria = categoria;
        this.tipo = tipo;
        this.total = total;
    }

    /**
     * Getters
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Setters
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

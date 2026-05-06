package TFG_EMG.Horizonte_Economico.dto;

/**
 * Para representar cantidades monetarias con precisión
 */
import java.math.BigDecimal;

/**
 * DTO para mostrar la distribución del gasto por categorías
 */
public class CategoriaDistribucionDTO {

    /**
     * Nombre de la categoría (ej: alimentación, transporte)
     */
    private String categoria;

    /**
     * Total gastado en esa categoría
     */
    private BigDecimal total;

    /**
     * Porcentaje que representa sobre el total
     */
    private BigDecimal porcentaje;

    /**
     * Constructor vacío (necesario para serialización)
     */
    public CategoriaDistribucionDTO() {}

    /**
     * Constructor completo
     */
    public CategoriaDistribucionDTO(String categoria, BigDecimal total, BigDecimal porcentaje) {
        this.categoria = categoria;
        this.total = total;
        this.porcentaje = porcentaje;
    }

    /**
     * Getters
     */
    public String getCategoria() { return categoria; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getTotal() { return total; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getPorcentaje() { return porcentaje; }

    /**
     * Setters
     */
    public void setCategoria(String categoria) { this.categoria = categoria; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setTotal(BigDecimal total) { this.total = total; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }
}

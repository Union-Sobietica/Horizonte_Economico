package TFG_EMG.Horizonte_Economico.dto;

/**
 * Validaciones
 */
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Tipos de datos para importe y fecha
 */
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para crear o actualizar un ingreso
 */
public class IngresoCrearSolicitud {

    /**
     * ID de la categoría asociada al ingreso (obligatorio)
     */
    @NotNull(message = "La categoría es obligatoria")
    private Long categoriaId;

    /**
     * Importe del ingreso (debe ser mayor que 0)
     */
    @NotNull(message = "El importe es obligatorio")
    @DecimalMin(value = "0.01", message = "El importe debe ser mayor que 0")
    private BigDecimal importe;

    /**
     * Fecha en la que se recibe el ingreso
     */
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    /**
     * Descripción opcional del ingreso
     */
    @Size(max = 200, message = "La descripción no puede superar 200 caracteres")
    private String descripcion;

    /**
     * Getters
     */
    public Long getCategoriaId() { return categoriaId; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getImporte() { return importe; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public LocalDate getFecha() { return fecha; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getDescripcion() { return descripcion; }

    /**
     * Setters
     */
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setImporte(BigDecimal importe) { this.importe = importe; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

package TFG_EMG.Horizonte_Economico.dto;

/**
 * Enum que define el tipo de miembro (pareja, hijo, etc.)
 */
import TFG_EMG.Horizonte_Economico.model.entity.TipoMiembroFamiliar;

/**
 * Validaciones
 */
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Para representar importes monetarios
 */
import java.math.BigDecimal;

/**
 * DTO para crear un miembro de la familia
 */
public class MiembroFamiliarCrearSolicitud {

    /**
     * Tipo de miembro familiar (obligatorio)
     */
    @NotNull(message = "El tipo es obligatorio")
    private TipoMiembroFamiliar tipo;

    /**
     * Nombre del miembro (obligatorio, máximo 80 caracteres)
     */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre no puede superar 80 caracteres")
    private String nombre;

    /**
     * Ingreso del miembro (opcional, no puede ser negativo)
     */
    @DecimalMin(value = "0.00", message = "El ingreso no puede ser negativo")
    private BigDecimal ingreso;

    /**
     * Getters
     */
    public TipoMiembroFamiliar getTipo() { return tipo; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getNombre() { return nombre; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getIngreso() { return ingreso; }

    /**
     * Setters
     */
    public void setTipo(TipoMiembroFamiliar tipo) { this.tipo = tipo; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setIngreso(BigDecimal ingreso) { this.ingreso = ingreso; }
}

package TFG_EMG.Horizonte_Economico.dto;

/**
 * Enum que define si la categoría es de ingreso o gasto
 */
import TFG_EMG.Horizonte_Economico.model.entity.TipoCategoria;

/**
 * Validaciones
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO para crear una nueva categoría
 */
public class CategoriaCrearSolicitud {

    /**
     * Nombre de la categoría (obligatorio, máximo 60 caracteres)
     */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 60, message = "El nombre no puede superar 60 caracteres")
    private String nombre;

    /**
     * Tipo de categoría (INGRESO o GASTO)
     */
    @NotNull(message = "El tipo es obligatorio")
    private TipoCategoria tipo;

    /**
     * Getter del nombre
     */
    public String getNombre() { return nombre; }

    /**
     * Getter del tipo
     */
    public TipoCategoria getTipo() { return tipo; }

    /**
     * Setter del nombre
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Setter del tipo
     */
    public void setTipo(TipoCategoria tipo) { this.tipo = tipo; }
}

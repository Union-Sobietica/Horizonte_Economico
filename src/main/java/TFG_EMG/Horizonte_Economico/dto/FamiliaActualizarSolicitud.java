package TFG_EMG.Horizonte_Economico.dto;

/**
 * Enum que representa el estado civil del usuario
 */
import TFG_EMG.Horizonte_Economico.model.entity.EstadoCivil;

/**
 * Validación: campo obligatorio
 */
import jakarta.validation.constraints.NotNull;

/**
 * DTO para actualizar los datos básicos de la familia
 */
public class FamiliaActualizarSolicitud {

    /**
     * Estado civil del usuario (necesario para el perfil familiar)
     */
    @NotNull(message = "El estado civil es obligatorio")
    private EstadoCivil estadoCivil;

    /**
     * Getter
     */
    public EstadoCivil getEstadoCivil() { return estadoCivil; }

    /**
     * Setter
     */
    public void setEstadoCivil(EstadoCivil estadoCivil) { this.estadoCivil = estadoCivil; }
}

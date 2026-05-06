package TFG_EMG.Horizonte_Economico.dto;

/**
 * Enum que define los roles del usuario (USER, ADMIN)
 */
import TFG_EMG.Horizonte_Economico.model.entity.RolUsuario;

/**
 * Validación: campo obligatorio
 */
import jakarta.validation.constraints.NotNull;

/**
 * DTO usado por el admin para actualizar usuarios
 */
public class AdminUsuarioActualizarSolicitud {

    /**
     * Nuevo rol del usuario (obligatorio)
     */
    @NotNull(message = "El rol es obligatorio")
    private RolUsuario rol;

    /**
     * Estado del usuario (activo o inactivo) (obligatorio)
     */
    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;

    /**
     * Getter del rol
     */
    public RolUsuario getRol() { return rol; }

    /**
     * Getter del estado activo
     */
    public Boolean getActivo() { return activo; }

    /**
     * Setter del rol
     */
    public void setRol(RolUsuario rol) { this.rol = rol; }

    /**
     * Setter del estado activo
     */
    public void setActivo(Boolean activo) { this.activo = activo; }
}

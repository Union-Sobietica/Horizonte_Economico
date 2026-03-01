package TFG_EMG.Horizonte_Economico.dto;

import TFG_EMG.Horizonte_Economico.model.entity.RolUsuario;
import jakarta.validation.constraints.NotNull;

public class AdminUsuarioActualizarSolicitud {

    @NotNull(message = "El rol es obligatorio")
    private RolUsuario rol;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;

    public RolUsuario getRol() { return rol; }
    public Boolean getActivo() { return activo; }

    public void setRol(RolUsuario rol) { this.rol = rol; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
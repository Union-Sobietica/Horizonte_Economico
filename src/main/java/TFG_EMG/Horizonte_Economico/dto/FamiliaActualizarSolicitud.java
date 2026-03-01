package TFG_EMG.Horizonte_Economico.dto;

import TFG_EMG.Horizonte_Economico.model.entity.EstadoCivil;
import jakarta.validation.constraints.NotNull;

public class FamiliaActualizarSolicitud {

    @NotNull(message = "El estado civil es obligatorio")
    private EstadoCivil estadoCivil;

    public EstadoCivil getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(EstadoCivil estadoCivil) { this.estadoCivil = estadoCivil; }
}
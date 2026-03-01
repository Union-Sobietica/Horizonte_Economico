package TFG_EMG.Horizonte_Economico.dto;

import TFG_EMG.Horizonte_Economico.model.entity.TipoCategoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoriaCrearSolicitud {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 60, message = "El nombre no puede superar 60 caracteres")
    private String nombre;

    @NotNull(message = "El tipo es obligatorio")
    private TipoCategoria tipo;

    public String getNombre() { return nombre; }
    public TipoCategoria getTipo() { return tipo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTipo(TipoCategoria tipo) { this.tipo = tipo; }
}
package TFG_EMG.Horizonte_Economico.dto;

import TFG_EMG.Horizonte_Economico.model.entity.TipoMiembroFamiliar;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class MiembroFamiliarCrearSolicitud {

    @NotNull(message = "El tipo es obligatorio")
    private TipoMiembroFamiliar tipo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre no puede superar 80 caracteres")
    private String nombre;

    @DecimalMin(value = "0.00", message = "El ingreso no puede ser negativo")
    private BigDecimal ingreso;

    public TipoMiembroFamiliar getTipo() { return tipo; }
    public String getNombre() { return nombre; }
    public BigDecimal getIngreso() { return ingreso; }

    public void setTipo(TipoMiembroFamiliar tipo) { this.tipo = tipo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setIngreso(BigDecimal ingreso) { this.ingreso = ingreso; }
}
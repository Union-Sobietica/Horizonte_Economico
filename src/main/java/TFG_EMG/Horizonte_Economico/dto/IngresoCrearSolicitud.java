package TFG_EMG.Horizonte_Economico.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IngresoCrearSolicitud {

    @NotNull(message = "El importe es obligatorio")
    @DecimalMin(value = "0.01", message = "El importe debe ser mayor que 0")
    private BigDecimal importe;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @Size(max = 120, message = "La fuente no puede superar 120 caracteres")
    private String fuente;

    public BigDecimal getImporte() { return importe; }
    public LocalDate getFecha() { return fecha; }
    public String getFuente() { return fuente; }

    public void setImporte(BigDecimal importe) { this.importe = importe; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setFuente(String fuente) { this.fuente = fuente; }
}
package TFG_EMG.Horizonte_Economico.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GastoCrearSolicitud {

    @NotNull(message = "La categoría es obligatoria")
    private Long categoriaId;

    @NotNull(message = "El importe es obligatorio")
    @DecimalMin(value = "0.01", message = "El importe debe ser mayor que 0")
    private BigDecimal importe;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @Size(max = 200, message = "La descripción no puede superar 200 caracteres")
    private String descripcion;

    public Long getCategoriaId() { return categoriaId; }
    public BigDecimal getImporte() { return importe; }
    public LocalDate getFecha() { return fecha; }
    public String getDescripcion() { return descripcion; }

    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
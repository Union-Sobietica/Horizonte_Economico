package TFG_EMG.Horizonte_Economico.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ingresos")
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "importe", nullable = false, precision = 12, scale = 2)
    private BigDecimal importe;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "fuente", length = 120)
    private String fuente;

    public Ingreso() {}

    public Ingreso(Usuario usuario, BigDecimal importe, LocalDate fecha, String fuente) {
        this.usuario = usuario;
        this.importe = importe;
        this.fecha = fecha;
        this.fuente = fuente;
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public BigDecimal getImporte() { return importe; }
    public LocalDate getFecha() { return fecha; }
    public String getFuente() { return fuente; }

    public void setId(Long id) { this.id = id; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setFuente(String fuente) { this.fuente = fuente; }
}
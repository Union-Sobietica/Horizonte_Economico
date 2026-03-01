package TFG_EMG.Horizonte_Economico.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gastos")
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(name = "importe", nullable = false, precision = 12, scale = 2)
    private BigDecimal importe;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    public Gasto() {}

    public Gasto(Usuario usuario, Categoria categoria, BigDecimal importe, LocalDate fecha, String descripcion) {
        this.usuario = usuario;
        this.categoria = categoria;
        this.importe = importe;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Categoria getCategoria() { return categoria; }
    public BigDecimal getImporte() { return importe; }
    public LocalDate getFecha() { return fecha; }
    public String getDescripcion() { return descripcion; }

    public void setId(Long id) { this.id = id; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
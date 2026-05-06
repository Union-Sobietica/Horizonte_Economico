package TFG_EMG.Horizonte_Economico.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad JPA que representa ingreso dentro del modelo de datos.
 */
@Entity
@Table(name = "ingresos")
public class Ingreso {

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

    /**
     * Inicializa las dependencias necesarias para Ingreso.
     */
    public Ingreso() {}

    /**
     * Inicializa las dependencias necesarias para Ingreso.
     */
    public Ingreso(Usuario usuario, Categoria categoria, BigDecimal importe, LocalDate fecha, String descripcion) {
        this.usuario = usuario;
        this.categoria = categoria;
        this.importe = importe;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public Long getId() { return id; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public Usuario getUsuario() { return usuario; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public Categoria getCategoria() { return categoria; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getImporte() { return importe; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public LocalDate getFecha() { return fecha; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getDescripcion() { return descripcion; }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setId(Long id) { this.id = id; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setImporte(BigDecimal importe) { this.importe = importe; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

package TFG_EMG.Horizonte_Economico.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Entidad JPA que representa miembro familiar dentro del modelo de datos.
 */
@Entity
@Table(name = "miembros_familiares")
public class MiembroFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_id", nullable = false)
    private Familia familia;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoMiembroFamiliar tipo;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @Column(name = "ingreso", precision = 12, scale = 2)
    private BigDecimal ingreso;

    /**
     * Inicializa las dependencias necesarias para MiembroFamiliar.
     */
    public MiembroFamiliar() {}

    /**
     * Inicializa las dependencias necesarias para MiembroFamiliar.
     */
    public MiembroFamiliar(Familia familia, TipoMiembroFamiliar tipo, String nombre, BigDecimal ingreso) {
        this.familia = familia;
        this.tipo = tipo;
        this.nombre = nombre;
        this.ingreso = ingreso;
    }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public Long getId() { return id; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public Familia getFamilia() { return familia; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public TipoMiembroFamiliar getTipo() { return tipo; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getNombre() { return nombre; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public BigDecimal getIngreso() { return ingreso; }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setId(Long id) { this.id = id; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setFamilia(Familia familia) { this.familia = familia; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setTipo(TipoMiembroFamiliar tipo) { this.tipo = tipo; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setIngreso(BigDecimal ingreso) { this.ingreso = ingreso; }
}

package TFG_EMG.Horizonte_Economico.model.entity;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa categoria dentro del modelo de datos.
 */
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoCategoria tipo;

    @Column(name = "activa", nullable = false)
    private Boolean activa = true;

    /**
     * Inicializa las dependencias necesarias para Categoria.
     */
    public Categoria() {}

    /**
     * Inicializa las dependencias necesarias para Categoria.
     */
    public Categoria(Usuario usuario, String nombre, TipoCategoria tipo) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.tipo = tipo;
        this.activa = true;
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
    public String getNombre() { return nombre; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public TipoCategoria getTipo() { return tipo; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public Boolean getActiva() { return activa; }

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
    public void setNombre(String nombre) { this.nombre = nombre; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setTipo(TipoCategoria tipo) { this.tipo = tipo; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setActiva(Boolean activa) { this.activa = activa; }
}

package TFG_EMG.Horizonte_Economico.model.entity;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa familia dentro del modelo de datos.
 */
@Entity
@Table(name = "familias")
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", nullable = false, length = 30)
    private EstadoCivil estadoCivil;

    /**
     * Inicializa las dependencias necesarias para Familia.
     */
    public Familia() {}

    /**
     * Inicializa las dependencias necesarias para Familia.
     */
    public Familia(Usuario usuario, EstadoCivil estadoCivil) {
        this.usuario = usuario;
        this.estadoCivil = estadoCivil;
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
    public EstadoCivil getEstadoCivil() { return estadoCivil; }

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
    public void setEstadoCivil(EstadoCivil estadoCivil) { this.estadoCivil = estadoCivil; }
}

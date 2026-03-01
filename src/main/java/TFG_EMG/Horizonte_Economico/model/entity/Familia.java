package TFG_EMG.Horizonte_Economico.model.entity;

import jakarta.persistence.*;

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

    public Familia() {}

    public Familia(Usuario usuario, EstadoCivil estadoCivil) {
        this.usuario = usuario;
        this.estadoCivil = estadoCivil;
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public EstadoCivil getEstadoCivil() { return estadoCivil; }

    public void setId(Long id) { this.id = id; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setEstadoCivil(EstadoCivil estadoCivil) { this.estadoCivil = estadoCivil; }
}
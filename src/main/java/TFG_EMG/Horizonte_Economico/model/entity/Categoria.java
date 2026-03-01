package TFG_EMG.Horizonte_Economico.model.entity;

import jakarta.persistence.*;

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

    public Categoria() {}

    public Categoria(Usuario usuario, String nombre, TipoCategoria tipo) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.tipo = tipo;
        this.activa = true;
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public String getNombre() { return nombre; }
    public TipoCategoria getTipo() { return tipo; }
    public Boolean getActiva() { return activa; }

    public void setId(Long id) { this.id = id; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTipo(TipoCategoria tipo) { this.tipo = tipo; }
    public void setActiva(Boolean activa) { this.activa = activa; }
}
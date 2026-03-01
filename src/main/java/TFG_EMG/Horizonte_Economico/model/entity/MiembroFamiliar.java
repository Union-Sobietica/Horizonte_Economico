package TFG_EMG.Horizonte_Economico.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

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

    public MiembroFamiliar() {}

    public MiembroFamiliar(Familia familia, TipoMiembroFamiliar tipo, String nombre, BigDecimal ingreso) {
        this.familia = familia;
        this.tipo = tipo;
        this.nombre = nombre;
        this.ingreso = ingreso;
    }

    public Long getId() { return id; }
    public Familia getFamilia() { return familia; }
    public TipoMiembroFamiliar getTipo() { return tipo; }
    public String getNombre() { return nombre; }
    public BigDecimal getIngreso() { return ingreso; }

    public void setId(Long id) { this.id = id; }
    public void setFamilia(Familia familia) { this.familia = familia; }
    public void setTipo(TipoMiembroFamiliar tipo) { this.tipo = tipo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setIngreso(BigDecimal ingreso) { this.ingreso = ingreso; }
}
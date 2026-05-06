package TFG_EMG.Horizonte_Economico.model.entity;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * Entidad JPA que representa usuario dentro del modelo de datos.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 20)
    private RolUsuario rol;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private Instant createdAt;

    @Column(name = "debe_cambiar_password", nullable = false)
    private Boolean debeCambiarPassword = false;

    /**
     * Inicializa las dependencias necesarias para Usuario.
     */
    public Usuario() {}

    /**
     * Inicializa las dependencias necesarias para Usuario.
     */
    public Usuario(String email, String passwordHash, RolUsuario rol) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.activo = true;
    }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public Long getId() { return id; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getEmail() { return email; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getPasswordHash() { return passwordHash; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public RolUsuario getRol() { return rol; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public Boolean getActivo() { return activo; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public Instant getCreatedAt() { return createdAt; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public Boolean getDebeCambiarPassword() { return debeCambiarPassword; }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setId(Long id) { this.id = id; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setEmail(String email) { this.email = email; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setRol(RolUsuario rol) { this.rol = rol; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setActivo(Boolean activo) { this.activo = activo; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setDebeCambiarPassword(Boolean debeCambiarPassword) { this.debeCambiarPassword = debeCambiarPassword; }
}

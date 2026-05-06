package TFG_EMG.Horizonte_Economico.dto;

/**
 * DTO que representa los datos del panel de administración
 */
public class AdminDashboardDTO {

    /**
     * Número total de usuarios en el sistema
     */
    private long totalUsuarios;

    /**
     * Usuarios activos
     */
    private long activos;

    /**
     * Usuarios desactivados
     */
    private long inactivos;

    /**
     * Usuarios con rol ADMIN
     */
    private long admins;

    /**
     * Usuarios con rol USER
     */
    private long usuarios;

    /**
     * Constructor vacío (necesario para frameworks como Spring)
     */
    public AdminDashboardDTO() {}

    /**
     * Constructor completo para inicializar todos los campos
     */
    public AdminDashboardDTO(long totalUsuarios, long activos, long inactivos, long admins, long usuarios) {
        this.totalUsuarios = totalUsuarios;
        this.activos = activos;
        this.inactivos = inactivos;
        this.admins = admins;
        this.usuarios = usuarios;
    }

    /**
     * Getters: permiten acceder a los datos
     */
    public long getTotalUsuarios() { return totalUsuarios; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public long getActivos() { return activos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public long getInactivos() { return inactivos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public long getAdmins() { return admins; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public long getUsuarios() { return usuarios; }

    /**
     * Setters: permiten modificar los datos
     */
    public void setTotalUsuarios(long totalUsuarios) { this.totalUsuarios = totalUsuarios; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setActivos(long activos) { this.activos = activos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setInactivos(long inactivos) { this.inactivos = inactivos; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setAdmins(long admins) { this.admins = admins; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setUsuarios(long usuarios) { this.usuarios = usuarios; }
}

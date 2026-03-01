package TFG_EMG.Horizonte_Economico.dto;

public class AdminDashboardDTO {
    private long totalUsuarios;
    private long activos;
    private long inactivos;
    private long admins;
    private long usuarios;

    public AdminDashboardDTO() {}

    public AdminDashboardDTO(long totalUsuarios, long activos, long inactivos, long admins, long usuarios) {
        this.totalUsuarios = totalUsuarios;
        this.activos = activos;
        this.inactivos = inactivos;
        this.admins = admins;
        this.usuarios = usuarios;
    }

    public long getTotalUsuarios() { return totalUsuarios; }
    public long getActivos() { return activos; }
    public long getInactivos() { return inactivos; }
    public long getAdmins() { return admins; }
    public long getUsuarios() { return usuarios; }

    public void setTotalUsuarios(long totalUsuarios) { this.totalUsuarios = totalUsuarios; }
    public void setActivos(long activos) { this.activos = activos; }
    public void setInactivos(long inactivos) { this.inactivos = inactivos; }
    public void setAdmins(long admins) { this.admins = admins; }
    public void setUsuarios(long usuarios) { this.usuarios = usuarios; }
}
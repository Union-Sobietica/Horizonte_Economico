package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.AdminDashboardDTO;
import TFG_EMG.Horizonte_Economico.dto.AdminUsuarioActualizarSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.RolUsuario;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.UsuarioRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio de negocio encargado de coordinar las reglas de admin.
 */
@Service
public class AdminServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    /**
     * Inicializa las dependencias necesarias para AdminServicio.
     */
    public AdminServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Ejecuta la operacion dashboard dentro del flujo de AdminServicio.
     */
    public AdminDashboardDTO dashboard() {
        long total = usuarioRepositorio.count();
        long activos = usuarioRepositorio.countByActivoTrue();
        long inactivos = usuarioRepositorio.countByActivoFalse();
        long admins = usuarioRepositorio.countByRol(RolUsuario.ADMIN);
        long usuarios = usuarioRepositorio.countByRol(RolUsuario.USUARIO);
        return new AdminDashboardDTO(total, activos, inactivos, admins, usuarios);
    }

    /**
     * Recupera la lista de datos solicitada para la vista o la API.
     */
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAllByOrderByCreatedAtDesc();
    }

    /**
     * Procesa la actualizacion del recurso indicado.
     */
    @Transactional
    public void actualizarUsuario(Long id, AdminUsuarioActualizarSolicitud solicitud) {
        Usuario u = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        u.setRol(solicitud.getRol());
        u.setActivo(solicitud.getActivo());
        usuarioRepositorio.save(u);
    }

    /**
     * Ejecuta la operacion desactivar dentro del flujo de AdminServicio.
     */
    @Transactional
    public void desactivar(Long id) {
        Usuario u = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        u.setActivo(false);
        usuarioRepositorio.save(u);
    }

    /**
     * Ejecuta la operacion activar dentro del flujo de AdminServicio.
     */
    @Transactional
    public void activar(Long id) {
        Usuario u = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        u.setActivo(true);
        usuarioRepositorio.save(u);
    }
}

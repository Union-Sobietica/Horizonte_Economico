package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.AdminDashboardDTO;
import TFG_EMG.Horizonte_Economico.dto.AdminUsuarioActualizarSolicitud;
import TFG_EMG.Horizonte_Economico.model.entity.RolUsuario;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.UsuarioRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    public AdminServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public AdminDashboardDTO dashboard() {
        long total = usuarioRepositorio.count();
        long activos = usuarioRepositorio.countByActivoTrue();
        long inactivos = usuarioRepositorio.countByActivoFalse();
        long admins = usuarioRepositorio.countByRol(RolUsuario.ADMIN);
        long usuarios = usuarioRepositorio.countByRol(RolUsuario.USUARIO);
        return new AdminDashboardDTO(total, activos, inactivos, admins, usuarios);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public void actualizarUsuario(Long id, AdminUsuarioActualizarSolicitud solicitud) {
        Usuario u = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        u.setRol(solicitud.getRol());
        u.setActivo(solicitud.getActivo());
        usuarioRepositorio.save(u);
    }

    @Transactional
    public void desactivar(Long id) {
        Usuario u = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        u.setActivo(false);
        usuarioRepositorio.save(u);
    }

    @Transactional
    public void activar(Long id) {
        Usuario u = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        u.setActivo(true);
        usuarioRepositorio.save(u);
    }
}
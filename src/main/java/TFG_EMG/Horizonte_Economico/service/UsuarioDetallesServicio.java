package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.UsuarioRepositorio;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * Servicio de negocio encargado de coordinar las reglas de usuario detalles.
 */
@Service
public class UsuarioDetallesServicio implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    /**
     * Inicializa las dependencias necesarias para UsuarioDetallesServicio.
     */
    public UsuarioDetallesServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Ejecuta la operacion loadUserByUsername dentro del flujo de UsuarioDetallesServicio.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(username.trim().toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String rolSpring = "ROLE_" + usuario.getRol().name();

        boolean activo = Boolean.TRUE.equals(usuario.getActivo());

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPasswordHash())
                .authorities(rolSpring)
                .disabled(!activo)
                .build();
    }
}

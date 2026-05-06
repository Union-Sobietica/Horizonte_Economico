package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.UsuarioRepositorio;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Servicio de negocio encargado de coordinar las reglas de usuario actual.
 */
@Service
public class UsuarioActualServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    /**
     * Inicializa las dependencias necesarias para UsuarioActualServicio.
     */
    public UsuarioActualServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Obtiene el recurso solicitado aplicando las validaciones necesarias.
     */
    public Usuario obtenerUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario autenticado no existe en BD"));
    }
}

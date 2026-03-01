package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.UsuarioRepositorio;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioActualServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioActualServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario obtenerUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario autenticado no existe en BD"));
    }
}
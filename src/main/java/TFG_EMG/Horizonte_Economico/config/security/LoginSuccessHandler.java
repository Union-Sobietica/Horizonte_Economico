package TFG_EMG.Horizonte_Economico.config.security;

import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.UsuarioRepositorio;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UsuarioRepositorio usuarioRepositorio;

    public LoginSuccessHandler(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String email = authentication.getName();

        Usuario usuario = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));

        if (Boolean.TRUE.equals(usuario.getDebeCambiarPassword())) {
            response.sendRedirect("/usuario/cambiar-password");
            return;
        }

        response.sendRedirect("/");
    }
}
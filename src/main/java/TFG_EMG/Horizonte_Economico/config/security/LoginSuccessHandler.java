package TFG_EMG.Horizonte_Economico.config.security;

/**
 * Entidad de usuario
 */
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;

/**
 * Repositorio para acceder a usuarios en BD
 */
import TFG_EMG.Horizonte_Economico.repository.UsuarioRepositorio;

/**
 * Clases HTTP
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Información de autenticación tras login
 */
import org.springframework.security.core.Authentication;

/**
 * Interfaz para manejar login exitoso
 */
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * Clase que define qué ocurre cuando el login es correcto
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Acceso a la base de datos de usuarios
     */
    private final UsuarioRepositorio usuarioRepositorio;

    /**
     * Inicializa las dependencias necesarias para LoginSuccessHandler.
     */
    public LoginSuccessHandler(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Gestiona el resultado del intento de autenticacion.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        /**
         * Obtiene el email del usuario autenticado
         */
        String email = authentication.getName();

        /**
         * Busca el usuario en la base de datos
         */
        Usuario usuario = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));

        /**
         * Si el usuario está obligado a cambiar contraseña
         */
        if (Boolean.TRUE.equals(usuario.getDebeCambiarPassword())) {
            /**
             * Redirige a la pantalla de cambio de contraseña
             */
            response.sendRedirect("/usuario/cambiar-password");
            return;
        }

        /**
         * Si todo está correcto, redirige a la página principal
         */
        response.sendRedirect("/");
    }
}

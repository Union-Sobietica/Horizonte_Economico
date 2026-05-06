package TFG_EMG.Horizonte_Economico.config.security;

/**
 * Clases para manejar peticiones HTTP
 */
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Excepción para cuentas deshabilitadas o bloqueadas
 */
import org.springframework.security.authentication.AccountStatusException;

/**
 * Excepción genérica de autenticación
 */
import org.springframework.security.core.AuthenticationException;

/**
 * Interfaz para gestionar fallos en el login
 */
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * Clase que gestiona qué ocurre cuando falla el inicio de sesión
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {

    /**
     * Gestiona el resultado del intento de autenticacion.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        /**
         * Si la cuenta está deshabilitada o bloqueada
         */
        if (exception instanceof AccountStatusException) {
            /**
             * Redirige indicando error específico de cuenta desactivada
             */
            response.sendRedirect("/login?error=disabled");
            return;
        }

        /**
         * Para cualquier otro error (credenciales incorrectas)
         */
        response.sendRedirect("/login?error=bad");
    }
}

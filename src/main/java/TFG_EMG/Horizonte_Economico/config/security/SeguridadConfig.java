package TFG_EMG.Horizonte_Economico.config.security;

import TFG_EMG.Horizonte_Economico.repository.UsuarioRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Permite usar configuraciones por defecto de Spring Security
 */
import org.springframework.security.config.Customizer;
/**
 * Configuración de seguridad HTTP
 */
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
/**
 * Cifrador BCrypt para contraseñas
 */
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * Interfaz para codificación de contraseñas
 */
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * Cadena principal de filtros de seguridad
 */
import org.springframework.security.web.SecurityFilterChain;

/**
 * Marca la clase como configuración de Spring
 */
@Configuration
public class SeguridadConfig {

    /**
     * Repositorio necesario para el handler de login correcto
     */
    private final UsuarioRepositorio usuarioRepositorio;

    /**
     * Inicializa las dependencias necesarias para SeguridadConfig.
     */
    public SeguridadConfig(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Ejecuta la operacion passwordEncoder dentro del flujo de SeguridadConfig.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        /**
         * Usa BCrypt para almacenar contraseñas cifradas de forma segura
         */
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        /**
                         * Rutas públicas accesibles sin iniciar sesión
                         */
                        .requestMatchers("/", "/login", "/registro").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        /**
                         * Endpoints abiertos en desarrollo para documentación y monitorización
                         */
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/actuator/health", "/actuator/info", "/actuator/metrics", "/actuator/metrics/**").permitAll()

                        /**
                         * Recomendaciones: requieren autenticación
                         */
                        .requestMatchers("/usuario/recomendaciones").authenticated()
                        .requestMatchers("/api/v1/recomendaciones/**").authenticated()

                        /**
                         * Ayudas: requieren autenticación
                         */
                        .requestMatchers("/usuario/ayudas").authenticated()
                        .requestMatchers("/api/v1/ayudas/**").authenticated()

                        /**
                         * Zona de administración: solo usuarios con rol ADMIN
                         */
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")

                        /**
                         * El resto de rutas requieren usuario autenticado
                         */
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        /**
                         * Página personalizada de login
                         */
                        .loginPage("/login")
                        /**
                         * Lógica al iniciar sesión correctamente
                         */
                        .successHandler(new LoginSuccessHandler(usuarioRepositorio))
                        /**
                         * Lógica al fallar el login
                         */
                        .failureHandler(new LoginFailureHandler())
                        .permitAll()
                )

                /**
                 * Logout con configuración por defecto
                 */
                .logout(Customizer.withDefaults())

                /**
                 * Página personalizada cuando el usuario no tiene permisos
                 */
                .exceptionHandling(ex -> ex.accessDeniedPage("/acceso-denegado"));

        return http.build();
    }
}

package TFG_EMG.Horizonte_Economico.config.security;

import TFG_EMG.Horizonte_Economico.repository.UsuarioRepositorio;
import TFG_EMG.Horizonte_Economico.service.AdminServicio;
import TFG_EMG.Horizonte_Economico.service.UsuarioServicio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SeguridadConfig {

    private final UsuarioRepositorio usuarioRepositorio;

    public SeguridadConfig(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/registro").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // Dev: documentación y actuator abiertos (en producción se cerrará)
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/actuator/health", "/actuator/info", "/actuator/metrics", "/actuator/metrics/**").permitAll()

                        .requestMatchers("/usuario/recomendaciones").authenticated()
                        .requestMatchers("/api/v1/recomendaciones/**").authenticated()

                        .requestMatchers("/usuario/ayudas").authenticated()
                        .requestMatchers("/api/v1/ayudas/**").authenticated()

                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(new LoginSuccessHandler(usuarioRepositorio))
                        .failureHandler(new LoginFailureHandler())
                        .permitAll()
                )
                .logout(Customizer.withDefaults())
                .exceptionHandling(ex -> ex.accessDeniedPage("/acceso-denegado"));


        return http.build();
    }
}
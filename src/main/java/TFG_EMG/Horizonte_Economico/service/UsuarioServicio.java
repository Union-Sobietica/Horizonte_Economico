package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.CambiarPasswordSolicitud;
import TFG_EMG.Horizonte_Economico.dto.RegistroSolicitud;
import TFG_EMG.Horizonte_Economico.exception.EmailYaRegistradoException;
import TFG_EMG.Horizonte_Economico.model.entity.RolUsuario;
import TFG_EMG.Horizonte_Economico.model.entity.Usuario;
import TFG_EMG.Horizonte_Economico.repository.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio, PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registrar(RegistroSolicitud solicitud) {
        final String email = solicitud.getEmail().trim().toLowerCase();

        if (!solicitud.getPassword().equals(solicitud.getPasswordRepetida())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        if (usuarioRepositorio.existsByEmail(email)) {
            throw new EmailYaRegistradoException("El email ya está registrado");
        }

        String hash = passwordEncoder.encode(solicitud.getPassword());
        Usuario usuario = new Usuario(email, hash, RolUsuario.USUARIO);
        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public String resetearPasswordPorAdmin(Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        String passwordTemporal = generarPasswordTemporal();
        usuario.setPasswordHash(passwordEncoder.encode(passwordTemporal));
        usuario.setDebeCambiarPassword(true);

        usuarioRepositorio.save(usuario);

        return passwordTemporal;
    }

    @Transactional
    public void cambiarPasswordUsuarioActual(CambiarPasswordSolicitud solicitud, Usuario usuario) {
        if (!passwordEncoder.matches(solicitud.getPasswordActual(), usuario.getPasswordHash())) {
            throw new IllegalArgumentException("La contraseña actual no es correcta");
        }

        if (!solicitud.getNuevaPassword().equals(solicitud.getRepetirNuevaPassword())) {
            throw new IllegalArgumentException("Las nuevas contraseñas no coinciden");
        }

        usuario.setPasswordHash(passwordEncoder.encode(solicitud.getNuevaPassword()));
        usuario.setDebeCambiarPassword(false);

        usuarioRepositorio.save(usuario);
    }

    private String generarPasswordTemporal() {
        return "Tmp-" + UUID.randomUUID().toString().substring(0, 8) + "_A";
    }
}
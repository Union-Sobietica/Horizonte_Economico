package TFG_EMG.Horizonte_Economico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Transporta datos de registro entre formularios, controladores y servicios.
 */
public class RegistroSolicitud {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    @Size(max = 150, message = "El email no puede superar 150 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 72, message = "La contraseña debe tener entre 8 y 72 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[-_!@#$%^&*()+=.,;:])[A-Za-z\\d\\-_!@#$%^&*()+=.,;:]{8,72}$",
            message = "La contraseña debe incluir mayúsculas, minúsculas y al menos un carácter especial permitido"
    )
    private String password;

    @NotBlank(message = "Repite la contraseña")
    private String passwordRepetida;

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getEmail() { return email; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getPassword() { return password; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getPasswordRepetida() { return passwordRepetida; }

    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setEmail(String email) { this.email = email; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setPassword(String password) { this.password = password; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setPasswordRepetida(String passwordRepetida) { this.passwordRepetida = passwordRepetida; }
}

package TFG_EMG.Horizonte_Economico.dto;

/**
 * Validaciones de campos
 */
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO para el cambio de contraseña del usuario
 */
public class CambiarPasswordSolicitud {

    /**
     * Contraseña actual (obligatoria)
     */
    @NotBlank(message = "La contraseña actual es obligatoria")
    private String passwordActual;

    /**
     * Nueva contraseña con reglas de seguridad
     */
    @NotBlank(message = "La nueva contraseña es obligatoria")
    @Size(min = 8, max = 72, message = "La contraseña debe tener entre 8 y 72 caracteres")
    @Pattern(
            /**
             * Debe contener: minúscula, mayúscula y carácter especial permitido
             */
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[-_!@#$%^&*()+=.,;:])[A-Za-z\\d\\-_!@#$%^&*()+=.,;:]{8,72}$",
            message = "La nueva contraseña debe incluir mayúsculas, minúsculas y al menos un carácter especial permitido"
    )
    private String nuevaPassword;

    /**
     * Confirmación de la nueva contraseña
     */
    @NotBlank(message = "Debes repetir la nueva contraseña")
    private String repetirNuevaPassword;

    /**
     * Getters
     */
    public String getPasswordActual() { return passwordActual; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getNuevaPassword() { return nuevaPassword; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public String getRepetirNuevaPassword() { return repetirNuevaPassword; }

    /**
     * Setters
     */
    public void setPasswordActual(String passwordActual) { this.passwordActual = passwordActual; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setNuevaPassword(String nuevaPassword) { this.nuevaPassword = nuevaPassword; }
    /**
     * Accede al dato usado por formularios, vistas o serializacion.
     */
    public void setRepetirNuevaPassword(String repetirNuevaPassword) { this.repetirNuevaPassword = repetirNuevaPassword; }
}

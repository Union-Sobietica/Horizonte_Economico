package TFG_EMG.Horizonte_Economico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CambiarPasswordSolicitud {

    @NotBlank(message = "La contraseña actual es obligatoria")
    private String passwordActual;

    @NotBlank(message = "La nueva contraseña es obligatoria")
    @Size(min = 8, max = 72, message = "La contraseña debe tener entre 8 y 72 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[-_!@#$%^&*()+=.,;:])[A-Za-z\\d\\-_!@#$%^&*()+=.,;:]{8,72}$",
            message = "La nueva contraseña debe incluir mayúsculas, minúsculas y al menos un carácter especial permitido"
    )
    private String nuevaPassword;

    @NotBlank(message = "Debes repetir la nueva contraseña")
    private String repetirNuevaPassword;

    public String getPasswordActual() { return passwordActual; }
    public String getNuevaPassword() { return nuevaPassword; }
    public String getRepetirNuevaPassword() { return repetirNuevaPassword; }

    public void setPasswordActual(String passwordActual) { this.passwordActual = passwordActual; }
    public void setNuevaPassword(String nuevaPassword) { this.nuevaPassword = nuevaPassword; }
    public void setRepetirNuevaPassword(String repetirNuevaPassword) { this.repetirNuevaPassword = repetirNuevaPassword; }
}
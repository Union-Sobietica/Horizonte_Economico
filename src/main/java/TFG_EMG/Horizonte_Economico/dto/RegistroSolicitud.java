package TFG_EMG.Horizonte_Economico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistroSolicitud {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    @Size(max = 150, message = "El email no puede superar 150 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 72, message = "La contraseña debe tener entre 8 y 72 caracteres")
    private String password;

    @NotBlank(message = "Repite la contraseña")
    private String passwordRepetida;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPasswordRepetida() { return passwordRepetida; }

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPasswordRepetida(String passwordRepetida) { this.passwordRepetida = passwordRepetida; }
}
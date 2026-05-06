package TFG_EMG.Horizonte_Economico.exception;

/**
 * Centraliza el manejo de errores relacionado con email ya registrado.
 */
public class EmailYaRegistradoException extends RuntimeException {
    /**
     * Inicializa las dependencias necesarias para EmailYaRegistradoException.
     */
    public EmailYaRegistradoException(String message) {
        super(message);
    }
}

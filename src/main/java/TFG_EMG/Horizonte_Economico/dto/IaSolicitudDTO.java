package TFG_EMG.Horizonte_Economico.dto;

/**
 * DTO para enviar solicitudes a un servicio de IA
 */
public class IaSolicitudDTO {

    /**
     * Texto o contexto que se envía a la IA (prompt)
     */
    private String prompt;

    /**
     * Constructor vacío
     */
    public IaSolicitudDTO() {}

    /**
     * Constructor con prompt
     */
    public IaSolicitudDTO(String prompt) {
        this.prompt = prompt;
    }

    /**
     * Getter del prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Setter del prompt
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}

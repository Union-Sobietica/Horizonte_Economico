package TFG_EMG.Horizonte_Economico.dto;

/**
 * DTO para encapsular respuestas generadas por la IA
 */
public class IaRespuestaDTO {

    /**
     * Texto generado (recomendaciones, ayudas, etc.)
     */
    private String contenido;

    /**
     * Constructor vacío
     */
    public IaRespuestaDTO() {}

    /**
     * Constructor con contenido
     */
    public IaRespuestaDTO(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Getter del contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Setter del contenido
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}

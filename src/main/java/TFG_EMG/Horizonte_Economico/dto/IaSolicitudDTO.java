package TFG_EMG.Horizonte_Economico.dto;

public class IaSolicitudDTO {
    private String prompt;

    public IaSolicitudDTO() {}
    public IaSolicitudDTO(String prompt) { this.prompt = prompt; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
}
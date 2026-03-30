package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.IaRespuestaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class IaClienteServicio {

    @Value("${openai.base-url}")
    private String baseUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();

    public IaRespuestaDTO generar(String prompt) {

        String url = baseUrl + "/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        headers.add("HTTP-Referer", "http://localhost:8087");
        headers.add("X-Title", "Horizonte Economico");

        Map<String, Object> body = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", "Eres un asesor financiero doméstico experto."),
                        Map.of("role", "user", "content", prompt)
                ),
                "temperature", 0.7
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url, request, Map.class);

        List choices = (List) response.getBody().get("choices");
        Map message = (Map) ((Map) choices.get(0)).get("message");

        return new IaRespuestaDTO((String) message.get("content"));
    }
}
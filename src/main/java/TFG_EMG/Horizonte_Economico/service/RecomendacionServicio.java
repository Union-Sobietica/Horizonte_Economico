package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.IaRespuestaDTO;
import TFG_EMG.Horizonte_Economico.dto.ResumenMensualDTO;
import org.springframework.stereotype.Service;

@Service
public class RecomendacionServicio {

    private final MetricaServicio metricaServicio;
    private final IaClienteServicio iaClienteServicio;

    public RecomendacionServicio(MetricaServicio metricaServicio,
                                 IaClienteServicio iaClienteServicio) {
        this.metricaServicio = metricaServicio;
        this.iaClienteServicio = iaClienteServicio;
    }

    public IaRespuestaDTO generarRecomendacion(String mes) {

        ResumenMensualDTO resumen = metricaServicio.resumenMensual(mes);

        String prompt = """
                Actúa como asesor financiero doméstico.

                Analiza estos datos mensuales:
                - Ingresos: %s
                - Gastos: %s
                - Sobrante: %s

                Devuelve la respuesta en español y en formato Markdown.
                Requisitos estrictos:
                - No repitas literalmente los datos de entrada más de una vez.
                - Sé claro, concreto y breve.
                - No uses introducciones largas.
                - No escribas frases genéricas como "Con base en los datos financieros que proporcionaste".
                - Estructura exacta:

                ## Diagnóstico
                Un párrafo breve valorando la situación.

                ## Puntos de mejora
                Una lista de 3 a 5 puntos concretos.

                ## Plan recomendado
                Una lista numerada de 3 pasos prácticos y realistas.

                ## Prioridad inmediata
                Una sola recomendación final, muy breve.

                No añadas despedidas ni texto extra.
                """.formatted(
                resumen.getTotalIngresos(),
                resumen.getTotalGastos(),
                resumen.getSobrante()
        );

        return iaClienteServicio.generar(prompt);
    }
}
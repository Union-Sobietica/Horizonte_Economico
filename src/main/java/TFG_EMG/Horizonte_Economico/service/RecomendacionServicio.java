package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.IaRespuestaDTO;
import TFG_EMG.Horizonte_Economico.dto.ResumenMensualDTO;
import org.springframework.stereotype.Service;

/**
 * Servicio de negocio encargado de coordinar las reglas de recomendacion.
 */
@Service
public class RecomendacionServicio {

    private final MetricaServicio metricaServicio;
    private final IaClienteServicio iaClienteServicio;

    /**
     * Inicializa las dependencias necesarias para RecomendacionServicio.
     */
    public RecomendacionServicio(MetricaServicio metricaServicio,
                                 IaClienteServicio iaClienteServicio) {
        this.metricaServicio = metricaServicio;
        this.iaClienteServicio = iaClienteServicio;
    }

    /**
     * Genera la respuesta o documento solicitado a partir de los datos disponibles.
     */
    public IaRespuestaDTO generarRecomendacion(String mes, String tipo) {
        return generarRecomendacion(mes, tipo, null);
    }

    /**
     * Genera la respuesta o documento solicitado a partir de los datos disponibles.
     */
    public IaRespuestaDTO generarRecomendacion(String mes, String tipo, String promptPersonalizado) {
        ResumenMensualDTO resumen = metricaServicio.resumenMensual(mes);

        String prompt = construirPrompt(tipo, resumen, promptPersonalizado);
        return iaClienteServicio.generar(prompt);
    }

    /**
     * Construye el contenido auxiliar necesario para completar la operacion.
     */
    private String construirPrompt(String tipo, ResumenMensualDTO resumen, String promptPersonalizado) {
        String base = """
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
                """.formatted(
                resumen.getTotalIngresos(),
                resumen.getTotalGastos(),
                resumen.getSobrante()
        );

        String promptUsuario = normalizarPromptPersonalizado(promptPersonalizado);
        if (promptUsuario != null) {
            return base + """

                    Solicitud personalizada del usuario:
                    %s

                    Responde a esa solicitud usando los datos mensuales anteriores.
                    Manten la respuesta en espanol y en formato Markdown.
                    No ignores las normas de prudencia, claridad y brevedad indicadas arriba.
                    """.formatted(promptUsuario);
        }

        return switch (tipo == null ? "general" : tipo) {
            case "inversion" -> base + """

                    Estructura exacta:

                    ## Diagnóstico
                    Un párrafo breve sobre la capacidad real del usuario para asumir inversión.

                    ## Opciones de inversión a vigilar
                    Lista de 3 opciones o sectores que estén siendo relevantes o estén en alza, explicadas con prudencia.
                    No prometas rentabilidad.
                    No des asesoramiento financiero agresivo.

                    ## Riesgos a tener en cuenta
                    Lista breve de riesgos.

                    ## Recomendación final
                    Una recomendación prudente y realista para empezar.
                    """;

            case "hogar" -> base + """

                    Estructura exacta:

                    ## Diagnóstico
                    Un párrafo breve sobre el gasto doméstico.

                    ## Oportunidades de ahorro en el hogar
                    Lista de 4 a 6 medidas concretas.
                    Incluye ejemplos como consumo eléctrico, horas valle, electrodomésticos, agua, climatización, suscripciones y compra doméstica.

                    ## Acciones prioritarias
                    Lista numerada de 3 pasos inmediatos.

                    ## Recomendación final
                    Una recomendación breve y práctica.
                    """;

            default -> base + """

                    Estructura exacta:

                    ## Diagnóstico
                    Un párrafo breve valorando la situación.

                    ## Puntos de mejora
                    Una lista de 3 a 5 puntos concretos.

                    ## Plan recomendado
                    Una lista numerada de 3 pasos prácticos y realistas.

                    ## Prioridad inmediata
                    Una sola recomendación final, muy breve.
                    """;
        };
    }

    /**
     * Valida y normaliza el texto recibido antes de usarlo.
     */
    private String normalizarPromptPersonalizado(String promptPersonalizado) {
        if (promptPersonalizado == null || promptPersonalizado.isBlank()) {
            return null;
        }

        String prompt = promptPersonalizado.trim();
        if (prompt.length() > 1000) {
            throw new IllegalArgumentException("El prompt personalizado no puede superar los 1000 caracteres.");
        }

        return prompt;
    }
}

package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.IaRespuestaDTO;
import TFG_EMG.Horizonte_Economico.model.entity.Familia;
import org.springframework.stereotype.Service;

/**
 * Servicio de negocio encargado de coordinar las reglas de ayuda.
 */
@Service
public class AyudaServicio {

    private final FamiliaServicio familiaServicio;
    private final IaClienteServicio iaClienteServicio;

    /**
     * Inicializa las dependencias necesarias para AyudaServicio.
     */
    public AyudaServicio(FamiliaServicio familiaServicio,
                         IaClienteServicio iaClienteServicio) {
        this.familiaServicio = familiaServicio;
        this.iaClienteServicio = iaClienteServicio;
    }

    /**
     * Analiza la informacion disponible y devuelve una recomendacion estructurada.
     */
    public IaRespuestaDTO analizarAyudas(String tipo) {
        return analizarAyudas(tipo, null);
    }

    /**
     * Analiza la informacion disponible y devuelve una recomendacion estructurada.
     */
    public IaRespuestaDTO analizarAyudas(String tipo, String promptPersonalizado) {
        Familia f = familiaServicio.obtenerOCrearSiNoExiste();
        var miembros = familiaServicio.listarMiembros();

        String prompt = construirPrompt(tipo, f, miembros.size(), promptPersonalizado);
        return iaClienteServicio.generar(prompt);
    }

    /**
     * Construye el contenido auxiliar necesario para completar la operacion.
     */
    private String construirPrompt(String tipo, Familia familia, int numeroMiembros, String promptPersonalizado) {
        String base = """
                Actúa como experto en ayudas sociales en España.

                Analiza este perfil familiar:

                Estado civil: %s
                Número de miembros: %s

                Devuelve la respuesta en español y en formato Markdown.
                No inventes leyes concretas.
                No des información falsa.
                Sé prudente y realista.
                """.formatted(
                familia.getEstadoCivil(),
                numeroMiembros
        );

        String promptUsuario = normalizarPromptPersonalizado(promptPersonalizado);
        if (promptUsuario != null) {
            return base + """

                    Solicitud personalizada del usuario:
                    %s

                    Responde a esa solicitud usando el perfil familiar anterior.
                    Manten la respuesta en espanol y en formato Markdown.
                    No inventes leyes concretas ni datos que no conozcas.
                    """.formatted(promptUsuario);
        }

        return switch (tipo == null ? "general" : tipo) {
            case "estudios" -> base + """

                    Estructura obligatoria:

                    ## Ayudas estudiantiles posibles
                    Lista de ayudas, becas o apoyos relacionados con estudios, formación o material educativo.

                    ## Requisitos clave
                    Lista breve de condiciones habituales.

                    ## Nivel de probabilidad
                    Alta / Media / Baja con explicación breve.

                    ## Recomendación
                    Qué debería revisar la familia a continuación.
                    """;

            case "hogar" -> base + """

                    Estructura obligatoria:

                    ## Ayudas del hogar posibles
                    Lista de ayudas relacionadas con vivienda, alquiler, suministros, familia numerosa o apoyo doméstico.

                    ## Requisitos clave
                    Lista breve de condiciones habituales.

                    ## Nivel de probabilidad
                    Alta / Media / Baja con explicación breve.

                    ## Recomendación
                    Qué debería revisar la familia a continuación.
                    """;

            default -> base + """

                    Estructura obligatoria:

                    ## Posibles ayudas
                    Lista de ayudas aplicables (nombre + breve descripción)

                    ## Requisitos clave
                    Lista de condiciones importantes

                    ## Nivel de probabilidad
                    Alta / Media / Baja con justificación breve

                    ## Recomendación
                    Qué debería hacer la familia ahora
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

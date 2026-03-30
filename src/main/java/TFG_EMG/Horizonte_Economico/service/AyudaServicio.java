package TFG_EMG.Horizonte_Economico.service;

import TFG_EMG.Horizonte_Economico.dto.IaRespuestaDTO;
import TFG_EMG.Horizonte_Economico.model.entity.Familia;
import org.springframework.stereotype.Service;

@Service
public class AyudaServicio {

    private final FamiliaServicio familiaServicio;
    private final IaClienteServicio iaClienteServicio;

    public AyudaServicio(FamiliaServicio familiaServicio,
                         IaClienteServicio iaClienteServicio) {
        this.familiaServicio = familiaServicio;
        this.iaClienteServicio = iaClienteServicio;
    }

    public IaRespuestaDTO analizarAyudas() {

        Familia f = familiaServicio.obtenerOCrearSiNoExiste();
        var miembros = familiaServicio.listarMiembros();

        String prompt = """
                Actúa como experto en ayudas sociales en España.

                Analiza este perfil familiar:

                Estado civil: %s
                Número de miembros: %s

                Devuelve la respuesta en español y en formato Markdown.

                Estructura obligatoria:

                ## Posibles ayudas
                Lista de ayudas aplicables (nombre + breve descripción)

                ## Requisitos clave
                Lista de condiciones importantes

                ## Nivel de probabilidad
                Alta / Media / Baja con justificación breve

                ## Recomendación
                Qué debería hacer la familia ahora

                No inventes leyes concretas.
                No des información falsa.
                Sé prudente y realista.
                """.formatted(
                f.getEstadoCivil(),
                miembros.size()
        );

        return iaClienteServicio.generar(prompt);
    }
}